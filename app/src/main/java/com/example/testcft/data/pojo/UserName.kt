package com.example.testcft.data.pojo

import com.google.gson.annotations.SerializedName

data class UserName(
    @SerializedName("title") var title: String? = null,
    @SerializedName("first") var first: String? = null,
    @SerializedName("last") var last: String? = null

)