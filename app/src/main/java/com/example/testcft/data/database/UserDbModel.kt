package com.example.testcft.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDbModel(

    @PrimaryKey
    val login: String,

    val fullName: String,

    val location: String,

    val email: String,

    val imageUrl: String,

    val age: Int,

    val phone: String,

    val gender: String
)