package com.example.testcft.data.pojo

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("street"      ) var street      : Street?      = Street(),
    @SerializedName("city"        ) var city        : String?      = null,
    @SerializedName("state"       ) var state       : String?      = null,
    @SerializedName("country"     ) var country     : String?      = null,
)
