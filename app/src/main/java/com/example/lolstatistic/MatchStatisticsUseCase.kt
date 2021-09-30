package com.example.lolstatistic

import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.details.MatchModel
import com.example.lolstatistic.match.MatchRepository

class MatchStatisticsUseCase(
    accountRepository: AccountRepository,
    val matchRepository: MatchRepository
) {
    val accountUseCase = AccountUseCase(accountRepository)
    var accountModel: AccountModel? = null
    var match: MatchModel? = null

    suspend fun getPuuid(name: String): String? {
        accountModel = accountUseCase.loadAccount(name)
        return accountModel?.puuid
    }

    suspend fun loadMatchList(puuid: String): List<String> {
        return matchRepository.getMatchListByPuuid(puuid).data?.toList() ?: listOf()
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        return matchRepository.getMatchByMatchId(
            String.format(
                "https://europe.api.riotgames.com/lol/match/v5/matches/%s",
                matchId
            )
        ).data
    }

    suspend fun getMatchList(name: String, startMatch: Int): MutableList<MatchModel> {
        val matchList = mutableListOf<MatchModel>()
        var matchIdList = listOf<String>()
        matchIdList = loadMatchList(
            String.format(
                "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%s&count=10",
                getPuuid(name).toString(), startMatch.toString()
            )
        )
        matchIdList.forEach {
            matchList.add(loadMatch(it) ?: MatchModel())
        }
        return matchList
    }
}