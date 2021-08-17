package com.example.lolstatistic.statistic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import kotlinx.coroutines.launch


class StatisticViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var matchesModel = MutableLiveData<MatchesModel>()
    fun getMatchStatistic(name: String) {
        viewModelScope.launch {
            matchesModel = matchStatisticsUseCase.getMatchStatistic(name)
        }
    }
}

