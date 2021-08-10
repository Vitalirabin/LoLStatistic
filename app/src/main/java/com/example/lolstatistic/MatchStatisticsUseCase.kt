package com.example.lolstatistic

import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.statistic.MatchesModel

class MatchStatisticsUseCase(val name: String) {
    val accountRepository = AccountRepository(ApiFactory.getApi())
    val matchRepository = MatchRepository(ApiFactory.getApi())
    var accountModel: AccountModel? = null
    var matchList: List<String>? = null
    var match: MatchModel? = null
    var matchesModel = MatchesModel()

    suspend fun loadAccount(name: String): AccountModel? {
        return accountRepository.getAccount(name).data
    }

    suspend fun loadMatchList(puuid: String): List<String>? {
        return matchRepository.getMatchListByPuuid(puuid).data
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        return matchRepository.getMatchByMatchId(matchId).data
    }

    suspend fun getMatchStatistic() {
        accountModel = loadAccount(name)
        matchList = loadMatchList(accountModel?.puuid.toString())
        matchList?.forEach {
            match = loadMatch(it)
            if (match?.info?.participants?.forEach {
                    if ()
                })
        }
    }
}