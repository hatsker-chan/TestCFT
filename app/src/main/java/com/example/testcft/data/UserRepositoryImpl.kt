package com.example.testcft.data

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.testcft.data.database.UserDao
import com.example.testcft.domain.User
import com.example.testcft.domain.UserRepository
import java.net.UnknownHostException

class UserRepositoryImpl(
    val mapper: UserMapper,
    val apiService: ApiService,
    val userDao: UserDao
) : UserRepository {

    override fun getUsersInfo(): LiveData<List<User>> {
        Log.d("My_test", userDao.getUsers().toString())
        return userDao.getUsers().map {
            it.map { userDb ->
                mapper.mapUserDbModelToEntity(userDb)
            }
        }
    }

    override fun getUserInfo(userLogin: String): LiveData<User> {
        return userDao.getUser(userLogin).map {
            mapper.mapUserDbModelToEntity(it)
        }
    }

    override suspend fun refreshData()  {

            val userData = apiService.loadUsers()
            val userList = mapper.mapResponseToUsersDto(userData)
            userDao.removeUsers()
            userList
                .map { mapper.mapUserDtoToDbModel(it) }
                .forEach {
                    userDao.insertUsers(it)
                }
    }
}