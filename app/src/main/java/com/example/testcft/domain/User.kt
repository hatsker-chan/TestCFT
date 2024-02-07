package com.example.testcft.domain


data class User(
    val login: String,
    val name: String,
    val phone: String,
    val location: String,
    val email: String,
    val imageUrl: String,
    val age: Int,
    val gender: String
) {
}