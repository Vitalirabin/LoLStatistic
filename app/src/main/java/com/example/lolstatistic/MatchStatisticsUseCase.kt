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

    suspend fun getMatchList(name: String, startMatch: Int) {
        var matchIdList = listOf<String>()
        matchIdList = loadMatchList(
            String.format(
                "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%s&count=10",
                getPuuid(name).toString(), startMatch.toString()
            )
        )
        Log.d("MatchStatisticsUseCase", "getMatchList")
        writingDataToTheDatabase(matchIdList)
    }

    suspend fun writingDataToTheDatabase(list: List<String>) {
        Log.d("MatchStatisticsUseCase", "writingDataToTheDatabase")
        list.forEach {
            val mode = matchRepository.getMatchByDB(it)?.gameMode
            if (mode == null) {
                matchRepository.loadMatch(it)
            }
        }
    }

    suspend fun getAllMatchFromDataBase(): MutableList<MatchModel> {
        Log.d("MatchStatisticsUseCase", "matchDao.getAllMatch()")
        val matchList = mutableListOf<MatchModel>()
        matchRepository.getAllMatchFromDB().forEach {
            val matchModel = MatchModel()
            matchModel.info.gameMode = it.gameMode.toString()
            matchModel.metadata.matchId = it.matchId
            matchList.add(matchModel)
        }
        return matchList
    }

    suspend fun loadMatch(id: String): MatchModel? {
        return matchRepository.loadMatch(id)
    }
}