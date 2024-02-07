package com.example.testcft.data.pojo

import com.google.gson.annotations.SerializedName

class Login(
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null,
)