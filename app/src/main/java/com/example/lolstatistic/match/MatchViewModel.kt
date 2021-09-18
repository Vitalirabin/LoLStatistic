package com.example.lolstatistic.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.example.lolstatistic.MatchStatisticsUseCase
import kotlinx.coroutines.launch


class MatchViewModel(val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var listOfMatch = MutableLiveData<List<MatchModel?>>()
    val muListOfMatch= mutableListOf<MatchModel?>()
    var startMatch = 0
    val match = MutableLiveData<MatchModel>()
    val participant = MutableLiveData<Participant>()

    fun loadMatchList(name: String): MutableLiveData<List<MatchModel?>> {
        viewModelScope.launch {
            listOfMatch.value = matchStatisticsUseCase.getMatchList(name, startMatch)
            startMatch += 10
        }
        return listOfMatch
    }

    fun getPuuid(): String {
        return matchStatisticsUseCase.accountModel?.puuid.toString()
    }

    fun updateList(name: String) {
        viewModelScope.launch {
            muListOfMatch.addAll(matchStatisticsUseCase.getMatchList(name, startMatch))
            listOfMatch.value = muListOfMatch.toList()
            startMatch += 10
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
}
