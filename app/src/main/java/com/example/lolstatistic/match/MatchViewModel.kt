package com.example.lolstatistic.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import kotlinx.coroutines.launch

class MatchViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var muListOfMatch = MutableLiveData<MutableList<MatchModel>>()
    var startMatch=0
    fun loadMatchList(name: String): MutableLiveData<MutableList<MatchModel>> {
        viewModelScope.launch {
            muListOfMatch.value = matchStatisticsUseCase.getMatchList(name, startMatch)
            startMatch+=10
        }
        return muListOfMatch
    }

    fun getPuuid(): String {
        return matchStatisticsUseCase.accountModel?.puuid.toString()
    }

    fun updateList(name: String) {
        viewModelScope.launch {
            muListOfMatch.value?.addAll(matchStatisticsUseCase.getMatchList(name,startMatch))
            muListOfMatch.value=muListOfMatch.value
            startMatch+=10
        }
    }
}
