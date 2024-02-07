package com.example.testcft.domain

class RefreshDataUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() {
        repository.refreshData()
    }
}