package com.example.testcft.domain

import androidx.lifecycle.LiveData

interface UserRepository {

    fun getUsersInfo(): LiveData<List<User>>

    fun getUserInfo(userLogin: String): LiveData<User>

    suspend fun refreshData()
}