package com.example.lolstatistic

import android.util.Log
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.match.details.MatchModel

class MatchStatisticsUseCase(
    accountRepository: AccountRepository,
    val matchRepository: MatchRepository,
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

    suspend fun getMatchList(name: String, startMatch: Int): List<MatchModel?> {
        Log.d("MatchStatisticsUseCase", "getMatchList")
        var matchIdList = listOf<String>()
        val matchList = mutableListOf<MatchModel?>()
        var matchListEnd = listOf<MatchModel?>()
        matchIdList = loadMatchList(
            String.format(
                "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%s&count=10",
                getPuuid(name).toString(), startMatch.toString()
            )
        )
        matchIdList.forEach {
            matchList.add(loadMatch(it))
        }
        matchListEnd = matchList.toList()
        return matchListEnd
    }

    suspend fun loadMatch(id: String): MatchModel? {
        return matchRepository.loadMatch(id, accountModel?.puuid ?: "")
    }
}