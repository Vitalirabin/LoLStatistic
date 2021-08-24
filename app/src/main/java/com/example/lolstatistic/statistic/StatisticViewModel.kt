package com.example.lolstatistic.statistic

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import kotlinx.coroutines.launch


class StatisticViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    // var matchesModel = MutableLiveData<MatchesModel>()
    var allMatches = MutableLiveData<Int>()
    var winMatches = MutableLiveData<Int>()
    var loseMatches = MutableLiveData<Int>()
    fun getMatchStatistic(name: String) {
        viewModelScope.launch {
            matchStatisticsUseCase.getMatchStatistic(name)
            allMatches = matchStatisticsUseCase.allMatches
            winMatches = matchStatisticsUseCase.winMatches
            loseMatches = matchStatisticsUseCase.loseMatches
        }
    }
}

