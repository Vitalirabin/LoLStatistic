package com.example.lolstatistic.statistic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import kotlinx.coroutines.launch


class StatisticViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    // var matchesModel = MutableLiveData<MatchesModel>()
    var allMatches = MutableLiveData<String>()
    var winMatches = MutableLiveData<String>()
    var loseMatches = MutableLiveData<String>()
    fun getMatchStatistic(name: String) {
        viewModelScope.launch {
            matchStatisticsUseCase.getMatchStatistic(name)
            allMatches.value = matchStatisticsUseCase.allMatches.toString()
            winMatches.value = matchStatisticsUseCase.winMatches.toString()
            loseMatches.value = matchStatisticsUseCase.loseMatches.toString()
        }
    }
}

