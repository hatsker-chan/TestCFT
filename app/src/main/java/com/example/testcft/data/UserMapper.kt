package com.example.testcft.data

import com.example.testcft.data.database.UserDbModel
import com.example.testcft.data.pojo.UserDto
import com.example.testcft.data.pojo.UserResponse
import com.example.testcft.domain.User

class UserMapper {

    fun mapResponseToUsersDto(response: UserResponse): List<UserDto> {
        return response.results
    }

    fun mapUserDtoToDbModel(userDto: UserDto): UserDbModel {
        return UserDbModel(
            login = userDto.login?.username ?: throw IllegalArgumentException(),
            fullName = getUserFullName(userDto),
            location = getLocationFromDto(userDto),
            email = userDto.email ?: "",
            imageUrl = userDto.picture?.large ?: throw IllegalArgumentException(),
            age = userDto.dob?.age ?: throw IllegalArgumentException(),
            phone = userDto.phone ?: throw IllegalArgumentException(),
            gender = userDto.gender ?: throw IllegalArgumentException()
        )
    }

    fun mapUserDbModelToEntity(userDbModel: UserDbModel): User{
        return User(
            login = userDbModel.login,
            name = userDbModel.fullName,
            location = userDbModel.location,
            email = userDbModel.email,
            imageUrl = userDbModel.imageUrl,
            age = userDbModel.age,
            phone = userDbModel.phone,
            gender = userDbModel.gender
        )
    }

    private fun getLocationFromDto(userDto: UserDto?): String {
        userDto?.let {
            val location = it.location ?: throw IllegalArgumentException()
            val country = location.country
            val city = location.city
            val streetName = location.street?.name
            val streetNumber = location.street?.number
            return "$country, $city, $streetName $streetNumber"
        }
        throw IllegalArgumentException()
    }

    private fun getUserFullName(userDto: UserDto?): String {
        userDto?.let {
            val userName = userDto.name ?: throw IllegalArgumentException()
            return "${userName.title}. ${userName.first} ${userName.last}"
        }
        throw IllegalArgumentException()
    }
}