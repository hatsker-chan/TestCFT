package com.example.testcft.domain

import androidx.lifecycle.LiveData

class GetUsersInfoUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(): LiveData<List<User>> {
        return repository.getUsersInfo()
    }
}