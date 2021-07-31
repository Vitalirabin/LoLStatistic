package com.example.lolstatistic.statistic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StatisticViewModel( val statisticRepository:StatisticRepository) : ViewModel() {
    var statisticModel = MutableLiveData<List<String>?>()
    fun loadMatchId(accountId:String) {
        Log.e("StatisticViewModel","loadPuuid")
        viewModelScope.launch {Log.e("StatisticViewModel","coroutine")
            statisticModel.value = withContext(Dispatchers.IO) {
                statisticRepository.getAllMovieList(accountId).data
            }
        }
    }
}

