package com.example.testcft.data.pojo

import com.google.gson.annotations.SerializedName

data class Dob (

    @SerializedName("date" ) var date : String? = null,
    @SerializedName("age"  ) var age  : Int?    = null

)