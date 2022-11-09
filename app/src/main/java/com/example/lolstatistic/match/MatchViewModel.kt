package com.example.lolstatistic.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.MatchStatisticsUseCase
import com.example.lolstatistic.match.details.MatchModel
import com.example.lolstatistic.match.details.Participant
import kotlinx.coroutines.launch


class MatchViewModel(private val matchStatisticsUseCase: MatchStatisticsUseCase) : ViewModel() {
    var listOfMatch = MutableLiveData<MutableList<MatchModel>>()
    val match = MutableLiveData<MatchModel>()
    val participant = MutableLiveData<Participant>()
    var isLoading = MutableLiveData<Boolean>()
    var puuid = ""

    fun getPuuidByAccount(name: String): String {
        isLoading.value = true
        if (puuid == "")
            viewModelScope.launch { puuid = matchStatisticsUseCase.getPuuid(name).toString() }
        isLoading.value = false
        return puuid
    }

    fun updateList(name: String) {
        if (isLoading.value!!) {
            return
        }
        viewModelScope.launch {
            isLoading.value = true
            val matchList = matchStatisticsUseCase.getAllMatchFromDataBase()
            if (matchList.size <= listOfMatch.value?.size ?: 1)
                matchStatisticsUseCase.getMatchList(name, listOfMatch.value?.size ?: 0)
            listOfMatch.value = matchStatisticsUseCase.getAllMatchFromDataBase()
            isLoading.value = false
        }
    }

    fun getParticipant(puuid: String, id: String) {
        viewModelScope.launch {
            match.value = matchStatisticsUseCase.loadMatch(id)
            match.value = match.value
            val index = match.value?.metadata?.participants?.indexOf(
                puuid
            )
            participant.value = match.value?.info?.participants?.get(index ?: 0)
        }
    }
}
