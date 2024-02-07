package com.example.testcft.data.pojo

import com.google.gson.annotations.SerializedName

data class UserDto(

    @SerializedName("gender") var gender: String? = null,
    @SerializedName("name") var name: UserName? = UserName(),
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("email") var email: String? = null,
    @SerializedName("login") var login: Login? = Login(),
    @SerializedName("dob") var dob: Dob? = Dob(),
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("cell") var cell: String? = null,
    @SerializedName("picture") var picture: Picture? = Picture(),
    @SerializedName("nat") var nat: String? = null
)