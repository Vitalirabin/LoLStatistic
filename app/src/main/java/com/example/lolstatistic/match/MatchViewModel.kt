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
    var isLoading = MutableLiveData<Boolean>()
    var puuid=""

    fun getPuuidByAccount():String{
        if (puuid=="")
        puuid=matchStatisticsUseCase.accountModel?.puuid.toString()
        return puuid
    }

    fun updateList(name: String) {
        if (isLoading.value!!)
            return
        viewModelScope.launch {
            isLoading.value = true
            val matchList = matchStatisticsUseCase.getAllMatchFromDataBase(getPuuidByAccount())
            if (matchList.size <= listOfMatch.value?.size ?: 1)
                matchStatisticsUseCase.getMatchList(name, listOfMatch.value?.size ?: 0)
            listOfMatch.value = matchStatisticsUseCase.getAllMatchFromDataBase(getPuuidByAccount())
            isLoading.value = false
        }
    }

    fun getParticipant(id: String) {
        viewModelScope.launch {
            match.value = matchStatisticsUseCase.loadMatch(id)
            match.value = match.value
            val index = match.value?.metadata?.participants?.indexOf(
                getPuuidByAccount()
            )
            participant.value = match.value?.info?.participants?.get(index ?: 0)
        }

    }
}
