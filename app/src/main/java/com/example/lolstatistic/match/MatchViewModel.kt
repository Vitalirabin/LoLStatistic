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

    fun loadMatchList(name: String) {
        if (isLoading)
            return
        viewModelScope.launch {
            isLoading = true
            listOfMatch.value = matchStatisticsUseCase.getMatchList(name, 0)
            isLoading = false
        }
        return
    }

    fun getPuuid(): String {
        return matchStatisticsUseCase.accountModel?.puuid.toString()
    }

    fun updateList(name: String) {
        if (isLoading)
            return
        viewModelScope.launch {
            isLoading = true
            val newListOfMatch = mutableListOf<MatchModel>()
            newListOfMatch.addAll(listOfMatch.value ?: emptyList())
            newListOfMatch.addAll(
                matchStatisticsUseCase.getMatchList(
                    name,
                    listOfMatch.value?.size ?: 0
                )
            )
            listOfMatch.value = newListOfMatch
            matchStatisticsUseCase.writingDataToTheDatabase(newListOfMatch)
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
}
