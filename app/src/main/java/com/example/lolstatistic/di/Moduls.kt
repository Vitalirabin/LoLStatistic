package com.example.lolstatistic.di

import com.example.lolstatistic.MatchStatisticsUseCase
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.account.AccountViewModel
import com.example.lolstatistic.enter.EnterViewModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.statistic.MatchesViewModel
import com.example.lolstatistic.statistic.StatisticViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val matchRepoModule = module(override = true) {
    single { ApiFactory.getApi() }
    single { MatchRepository(get()) }
}
val accountRepoModule = module(override = true) {
    single { ApiFactory.getApi() }
    single { AccountRepository(get()) }
}
val statisticViewModule = module(override = true) {
    viewModel { StatisticViewModel(get()) }
}
val accountViewModule = module(override = true) {
    viewModel { AccountViewModel() }
}
val matchViewModule = module(override = true) {
    viewModel { MatchesViewModel() }
}
val enterViewModule = module(override = true) {
    viewModel { EnterViewModel() }
}
val matchUseCase = module(override = true) {
    single { MatchStatisticsUseCase(get(), get()) }
}
val accountUseCase = module(override = true) {
    single { AccountUseCase(get()) }
}