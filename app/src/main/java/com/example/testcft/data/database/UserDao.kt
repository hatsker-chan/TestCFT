package com.example.testcft.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testcft.domain.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<UserDbModel>>

    @Query("SELECT * FROM users WHERE login =:selectedUserLogin")
    fun getUser(selectedUserLogin: String): LiveData<UserDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: UserDbModel)

    @Query("DELETE From users")
    suspend fun removeUsers()
}