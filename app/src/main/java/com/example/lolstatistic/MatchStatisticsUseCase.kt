package com.example.lolstatistic

import androidx.lifecycle.MutableLiveData
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.statistic.MatchesModel

class MatchStatisticsUseCase(
    accountRepository: AccountRepository,
    val matchRepository: MatchRepository
) {
    val accountUseCase = AccountUseCase(accountRepository)
    var accountModel: AccountModel? = null
    var matchListIdPrev: String? = null
    var matchList = mutableListOf<String>()
    var match: MatchModel? = null
    var matchesModel = MutableLiveData<MatchesModel>()
    var allMatches = 0
    var winMatches = 0
    var loseMatches = 0
    suspend fun getPuuid(name: String): String? {
        accountModel = accountUseCase.loadAccount(name)
        return accountModel?.puuid
    }

    suspend fun loadMatchList(puuid: String): String {
        return matchRepository.getMatchListByPuuid(puuid).data.toString()
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        return matchRepository.getMatchByMatchId(
            String.format(
                "https://europe.api.riotgames.com/lol/match/v5/matches/%s",
                matchId
            )
        ).data
    }

    suspend fun getMatchesId(name: String): MutableList<String> {
        accountModel = accountUseCase.loadAccount(name)
        var startMatch = 0
        do {
            matchListIdPrev = loadMatchList(
                String.format(
                    "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%s&count=5",
                    accountModel?.puuid.toString(), startMatch.toString()
                )
            )
            matchList.add(matchListIdPrev ?: "")
            startMatch = startMatch + 5
        } while (startMatch != 50)
        return matchList
    }
}