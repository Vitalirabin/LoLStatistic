package com.example.lolstatistic.statistic

import com.example.lolstatistic.BaseViewModelFactory

class StatisticViewModelFactory:BaseViewModelFactory<StatisticViewModel>(StatisticViewModel::class.java) {
    override fun createViewModel(): StatisticViewModel {
        return StatisticViewModel()
    }
}