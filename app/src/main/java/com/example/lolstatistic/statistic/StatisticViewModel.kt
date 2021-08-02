package com.example.lolstatistic.statistic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.match.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StatisticViewModel( val statisticRepository: MatchRepository) : ViewModel() {
    var statisticModel = MutableLiveData<List<String>?>()
    fun loadMatchId(puuid:String) {
        Log.e("StatisticViewModel","loadPuuid")
        viewModelScope.launch {Log.e("StatisticViewModel","coroutine")
            statisticModel.value = withContext(Dispatchers.IO) {
                statisticRepository.getMatchList(puuid).data
            }
        }
    }
}

