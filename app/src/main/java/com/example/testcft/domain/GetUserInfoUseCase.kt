package com.example.testcft.domain

class GetUserInfoUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(userLogin: String){
        repository.getUserInfo(userLogin)
    }
}