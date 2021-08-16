package com.example.lolstatistic

import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.statistic.MatchesModel

class MatchStatisticsUseCase(val accountRepository: AccountRepository,val matchRepository:MatchRepository) {
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

    suspend fun getMatchStatistic(name: String) {
        accountModel = accountUseCase.loadAccount(name)
        matchList = loadMatchList(accountModel?.puuid.toString())
        matchList?.forEach {
            match = loadMatch(it)
        }
    }
}