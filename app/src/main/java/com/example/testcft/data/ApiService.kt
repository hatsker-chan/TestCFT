package com.example.testcft.data

import com.example.testcft.data.pojo.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("?results=20")
    suspend fun loadUsers(): UserResponse
}