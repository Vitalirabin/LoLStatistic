package com.example.lolstatistic.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import kotlinx.coroutines.launch

class MatchViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var matchModel: MatchModel? = null
    var listOfMatchId: List<String>? = null
    var listOfMatch: MutableList<MatchModel?>? = null
    fun loadMatchIdList(name: String) {
        viewModelScope.launch {
            listOfMatchId =
                matchStatisticsUseCase.loadMatchList(matchStatisticsUseCase.getPuuid(name) ?: "")
        }
    }

    fun loadMatch(matchId: String): MatchModel? {
        viewModelScope.launch {
            matchModel = matchStatisticsUseCase.loadMatch(matchId)
        }
        return matchModel
    }

    fun loadMatchList(name: String): MutableList<MatchModel?>? {
        loadMatchIdList(name)
        listOfMatchId?.forEach {
            listOfMatch?.add(loadMatch(it))
        }
        return listOfMatch
    }

    fun getPuuid(name: String): String? {
        var puuid: String? = null
        viewModelScope.launch {
            puuid = matchStatisticsUseCase.getPuuid(name)
        }
        return puuid
    }
}
