package com.example.lolstatistic

import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository

class MatchStatisticsUseCase(
    accountRepository: AccountRepository,
    val matchRepository: MatchRepository
) {
    val accountUseCase = AccountUseCase(accountRepository)
    var accountModel: AccountModel? = null
    var matchListIdPrev = listOf<String>()
    var matchIdList = mutableListOf<String>()
    var match: MatchModel? = null
    var allMatches = 0
    var winMatches = 0
    var loseMatches = 0
    var matchList = mutableListOf<MatchModel>()
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

    suspend fun getMatchList(name: String): MutableList<MatchModel> {
        var startMatch = 0
        do {
            matchListIdPrev = loadMatchList(
                String.format(
                    "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%s&count=2",
                    getPuuid(name).toString(), startMatch.toString()
                )
            )
            matchIdList.addAll(matchListIdPrev)
            startMatch = startMatch + 2
        } while (startMatch != 10)
        matchIdList.forEach {
            matchList.add(loadMatch(it) ?: MatchModel())
        }
        return matchList
    }
}