package com.example.lolstatistic.statistic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StatisticViewModel(val useCase: MatchStatisticsUseCase, val name: String) : ViewModel() {
    var accountModel:AccountModel?=null
    var matchList:List<String>?=null
    val match=MutableLiveData<MatchModel>()
    var matchesModel=MatchesModel()
    suspend fun getMatchStatistic(){
        accountModel=useCase.loadAccount(name)
        matchList=useCase.loadMatchList(accountModel?.puuid.toString())
        matchList?.forEach {
            matchesModel.allMatches
            useCase.loadMatch(it)
        }
    }
}

