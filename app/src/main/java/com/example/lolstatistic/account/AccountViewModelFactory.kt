package com.example.lolstatistic.account

import com.example.lolstatistic.BaseViewModelFactory

class AccountViewModelFactory:BaseViewModelFactory<AccountViewModel>(AccountViewModel::class.java) {
    override fun createViewModel(): AccountViewModel {
        return AccountViewModel()
    }
}