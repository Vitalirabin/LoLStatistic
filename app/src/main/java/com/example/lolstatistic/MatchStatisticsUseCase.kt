package com.example.lolstatistic

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
    var matchesModel = MatchesModel()
    suspend fun loadMatchList(puuid: String): List<String>? {
        return matchRepository.getMatchListByPuuid(puuid).data
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        return matchRepository.getMatchByMatchId(matchId).data
    }

    suspend fun getMatchStatistic(name: String):MatchesModel {
        accountModel = accountUseCase.loadAccount(name)
        matchList = loadMatchList(accountModel?.puuid.toString())
        matchList?.forEach {
            match = loadMatch(it)
            matchesModel.allMatches.value=+1
            if (match?.info?.participants?.get(match?.metadata?.participants?.indexOf(accountModel?.puuid)?:0)?.win==true){
                matchesModel.winMatches.value=+1}
            else matchesModel.loseMatches.value=+1
        }
        return matchesModel
    }
}