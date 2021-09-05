package com.example.lolstatistic.match

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import kotlinx.coroutines.launch

class MatchViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var matchModel: MatchModel? = null
    var listOfMatchId: List<String>? = null
    var listOfMatch: MutableList<MatchModel?>? = null
    var muListOfMatch = MutableLiveData<List<MatchModel?>?>()

    fun loadMatch(matchId: String): MatchModel? {
        viewModelScope.launch {
            matchModel = matchStatisticsUseCase.loadMatch(matchId)
        }
        return matchModel
    }

    fun loadMatchList(name: String): MutableLiveData<List<MatchModel?>?> {
        viewModelScope.launch {
            listOfMatchId = matchStatisticsUseCase.getMatchesId(getPuuid(name) ?: "")
        }
        Log.d("LoadMatchList", listOfMatchId.toString())
        listOfMatchId?.forEach {
            Log.d("IdList", it ?: "пусто")
            listOfMatch?.add(loadMatch(it ?: ""))
        }
        muListOfMatch.value = listOfMatch
        return muListOfMatch
    }

    fun getPuuid(name: String): String? {
        var puuid: String? = null
        viewModelScope.launch {
            puuid = matchStatisticsUseCase.getPuuid(name)
        }
        return puuid
    }
}
