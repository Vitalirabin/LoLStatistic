package com.example.lolstatistic

import androidx.lifecycle.MutableLiveData
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.statistic.MatchesModel

class MatchStatisticsUseCase(val accountRepository: AccountRepository, val matchRepository: MatchRepository) {
    val accountUseCase = AccountUseCase(accountRepository)
    var accountModel: AccountModel? = null
    var matchList: List<String>? = null
    var match: MatchModel? = null
    var matchesModel = MutableLiveData<MatchesModel>()
    val allMatches=MutableLiveData<Int>()
    val winMatches=MutableLiveData<Int>()
    val loseMatches=MutableLiveData<Int>()
    suspend fun loadMatchList(puuid: String): List<String>? {
        return matchRepository.getMatchListByPuuid(puuid).data
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        return matchRepository.getMatchByMatchId(String.format("https://europe.api.riotgames.com/lol/match/v5/matches/%s",matchId)).data
    }

    suspend fun getMatchStatistic(name: String):MutableLiveData<MatchesModel> {
        allMatches.value=0
        winMatches.value=0
        loseMatches.value=0
        accountModel = accountUseCase.loadAccount(name)
        matchList = loadMatchList(String.format("https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids",accountModel?.puuid.toString()))
        matchList?.forEach {
            match = loadMatch(it)
           // matchesModel.value?.allMatches=+1
            allMatches.value=+1
            if (match?.info?.participants?.get(match?.metadata?.participants?.indexOf(accountModel?.puuid)?:0)?.win==true){
                winMatches.value=+1
            }
          //  if (match?.info!=null){
              //  matchesModel.value?.winMatches=+1}
            else //matchesModel.value?.loseMatches=+1
              loseMatches.value=+1
        }
        return matchesModel
    }
}