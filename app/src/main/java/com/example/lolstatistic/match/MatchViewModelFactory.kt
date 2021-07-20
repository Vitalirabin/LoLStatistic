package com.example.lolstatistic.match

import com.example.lolstatistic.BaseViewModelFactory
import com.example.lolstatistic.account.AccountViewModel

class MatchViewModelFactory: BaseViewModelFactory<MatchViewModel>(MatchViewModel::class.java) {
    override fun createViewModel(): MatchViewModel {
        return MatchViewModel()
    }
}