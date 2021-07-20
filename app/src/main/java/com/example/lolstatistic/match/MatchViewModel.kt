package com.example.lolstatistic.match

import android.service.autofill.FieldClassification
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchViewModel: ViewModel() {
    val matchRepository = MatchRepository(ApiFactory.getApi())
    val matchModel = MutableLiveData<MatchModel?>()
    fun loadMatch(matchId:String) {
        viewModelScope.launch {
            matchModel.value = withContext(Dispatchers.IO) {
                matchRepository.getAllMovieList(matchId).data
            }
        }
    }
}