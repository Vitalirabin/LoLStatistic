package com.example.lolstatistic.enter

import com.example.lolstatistic.BaseViewModelFactory

class EnterViewModelFactory:BaseViewModelFactory<EnterViewModel>(EnterViewModel::class.java) {
    override fun createViewModel(): EnterViewModel {
        return EnterViewModel()
    }
}