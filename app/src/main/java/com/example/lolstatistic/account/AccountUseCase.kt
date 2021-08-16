package com.example.lolstatistic.account

class AccountUseCase(val accountRepository:AccountRepository) {
    suspend fun loadAccount(name: String): AccountModel? {
        return accountRepository.getAccount(name).data
    }
}