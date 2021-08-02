package com.example.lolstatistic.di

import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.statistic.StatisticViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { ApiFactory.getApi() }
    single { MatchListRepository(get()) }
}
val statisticViewModule= module {
    viewModel { StatisticViewModel(get()) }
}