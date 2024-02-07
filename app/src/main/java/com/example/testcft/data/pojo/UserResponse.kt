package com.example.testcft.data.pojo

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results") var results: ArrayList<UserDto> = arrayListOf()
)