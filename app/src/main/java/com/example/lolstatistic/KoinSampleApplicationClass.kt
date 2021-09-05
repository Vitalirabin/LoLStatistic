package com.example.lolstatistic

import android.app.Application
import com.example.lolstatistic.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinSampleApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinSampleApplicationClass)
            androidLogger()
            modules(
                listOf(
                    matchRepoModule, accountRepoModule,
                    enterViewModule, matchUseCase, accountUseCase,
                    matchViewModule
                )
            )
        }
    }

}