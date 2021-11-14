package com.example.lolstatistic.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import com.example.lolstatistic.match.details.MatchModel
import com.example.lolstatistic.match.details.Participant
import kotlinx.coroutines.launch


class MatchViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var listOfMatch = MutableLiveData<MutableList<MatchModel>>()
    val match = MutableLiveData<MatchModel>()
    val participant = MutableLiveData<Participant>()
    var isLoading = false

    fun getPuuid(): String {
        return matchStatisticsUseCase.accountModel?.puuid.toString()
    }

    fun updateList(name: String) {
        if (isLoading)
            return
        viewModelScope.launch {
            isLoading = true
            val matchList = getAllMatchesFromDB()
            if (matchList.size <= listOfMatch.value?.size ?: 1)
                matchStatisticsUseCase.getMatchList(name, listOfMatch.value?.size ?: 0)
            listOfMatch.value = getAllMatchesFromDB()
            isLoading = false
        }
    }

    fun getParticipant(id: String) {
        viewModelScope.launch {
            match.value = matchStatisticsUseCase.loadMatch(id)
            match.value = match.value
            val index = match.value?.metadata?.participants?.indexOf(
                getPuuid()
            )
            participant.value = match.value?.info?.participants?.get(index ?: 0)
        }

    }

    suspend fun getAllMatchesFromDB(): MutableList<MatchModel> {
        val matchList = mutableListOf<MatchModel>()
        matchStatisticsUseCase.getAllMatchFromDataBase().forEach {
            val matchModel = MatchModel()
            matchModel.info.gameMode = it.gameMode.toString()
            matchModel.metadata.matchId = it.matchId
            matchList.add(matchModel)
        }
        return matchList
    }
}
