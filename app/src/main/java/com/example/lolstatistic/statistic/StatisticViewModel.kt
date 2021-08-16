package com.example.lolstatistic.statistic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StatisticViewModel(val accountUseCase: AccountUseCase, val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var accountModel:AccountModel?=null
    var matchList:List<String>?=null
    val match=MutableLiveData<MatchModel>()
    var matchesModel=MatchesModel()
    suspend fun getMatchStatistic(name: String){
        accountModel=accountUseCase.loadAccount(name)
        matchList=matchStatisticsUseCase.loadMatchList(accountModel?.puuid.toString())
        matchList?.forEach {
            matchesModel.allMatches
            matchStatisticsUseCase.loadMatch(it)
        }
    }
}

