package com.example.testcft.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testcft.data.ApiFactory
import com.example.testcft.data.UserMapper
import com.example.testcft.data.UserRepositoryImpl
import com.example.testcft.data.database.AppDatabase
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class UserViewModel(application: Application) : AndroidViewModel(
    application
) {

    private val repository = UserRepositoryImpl(
        UserMapper(),
        ApiFactory.apiService,
        AppDatabase.getInstance(application).getUserDao()
    )

    val users = repository.getUsersInfo()

    private val _isConnectError = MutableLiveData(false)
    val isConnectError: LiveData<Boolean> = _isConnectError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUser(userLogin: String) = repository.getUserInfo(userLogin)

    fun refreshData() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isConnectError.value = false
                repository.refreshData()
            } catch (e: UnknownHostException) {
                _isConnectError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    init {
        if (users.value?.isEmpty() == true) {
            refreshData()
        }
    }
}