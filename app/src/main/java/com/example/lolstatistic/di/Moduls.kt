package com.example.lolstatistic.di

import com.example.lolstatistic.MatchStatisticsUseCase
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountViewModel
import com.example.lolstatistic.enter.EnterViewModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.statistic.MatchesViewModel
import com.example.lolstatistic.statistic.StatisticViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val matchRepoModule = module {
    single { ApiFactory.getApi() }
    single { MatchRepository(get()) }
}
val accountRepoModule = module {
    single { ApiFactory.getApi() }
    single { AccountRepository(get()) }
}
val statisticViewModule = module {
    viewModel { StatisticViewModel(get(), get()) }
}
val accountViewModule = module {
    viewModel { AccountViewModel() }
}
val matchViewModule = module {
    viewModel { MatchesViewModel() }
}
val enterViewModule = module {
    viewModel { EnterViewModel() }
}
val useCase = module {
    single { MatchStatisticsUseCase(get(), get()) }
}