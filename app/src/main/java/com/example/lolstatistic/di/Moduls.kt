package com.example.lolstatistic.di

import android.app.Application
import androidx.room.Room
import com.example.lolstatistic.Constants.MATCH_DATA_BASE
import com.example.lolstatistic.MatchStatisticsUseCase
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.data_base.MatchDao
import com.example.lolstatistic.enter.EnterViewModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.match.MatchViewModel
import com.example.lolstatistic.data_base.MatchDataBase
import com.example.lolstatistic.network.ApiFactory
import org.koin.android.ext.koin.androidApplication
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
val enterViewModule = module(override = true) {
    viewModel { EnterViewModel() }
}
val matchUseCase = module(override = true) {
    single { MatchStatisticsUseCase(get(), get(), get()) }
}
val accountUseCase = module(override = true) {
    single { AccountUseCase(get()) }
}
val matchViewModule = module(override = true) {
    viewModel { MatchViewModel(get()) }
}
val matchDataBaseModule = module(override = true) {
    fun provideDataBase(application: Application): MatchDataBase {
        return Room.databaseBuilder(application, MatchDataBase::class.java, MATCH_DATA_BASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: MatchDataBase): MatchDao {
        return dataBase.matchesDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}