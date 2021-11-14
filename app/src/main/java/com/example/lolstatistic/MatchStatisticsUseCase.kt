package com.example.lolstatistic

import android.util.Log
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.data_base.MatchDataBase
import com.example.lolstatistic.data_base.MatchModelForDataBase
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.match.details.MatchModel

class MatchStatisticsUseCase(
    accountRepository: AccountRepository,
    val matchRepository: MatchRepository,
    db: MatchDataBase
) {
    val accountUseCase = AccountUseCase(accountRepository)
    var accountModel: AccountModel? = null
    var match: MatchModel? = null
    var matchDao = db.matchesDao
    suspend fun getPuuid(name: String): String? {
        accountModel = accountUseCase.loadAccount(name)
        return accountModel?.puuid
    }

    suspend fun loadMatchList(puuid: String): List<String> {
        return matchRepository.getMatchListByPuuid(puuid).data?.toList() ?: listOf()
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        Log.d("MatchStatisticsUseCase", "loadMatch")
        if (readFromDataBase(matchId).info.gameId == null) {
            val match = matchRepository.getMatchByMatchId(
                String.format(
                    "https://europe.api.riotgames.com/lol/match/v5/matches/%s",
                    matchId
                )
            ).data
            writingMatchToTheDataBase(match ?: MatchModel())
            return match
        } else return readFromDataBase(matchId)
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
            val mode = matchDao.getById(it)?.gameMode
            if (mode == null) {
                loadMatch(it)
            }
        }
    }

    suspend fun writingMatchToTheDataBase(match: MatchModel) {
        Log.d("MatchStatisticsUseCase", "writingMatchToTheDataBase")
        val matchBase = MatchModelForDataBase()
        matchBase.matchId = match.metadata.matchId.toString()
        matchBase.gameMode = match.info.gameMode
        matchDao.addData(matchBase)
    }

    suspend fun getAllMatchFromDataBase(): List<MatchModelForDataBase> {
       Log.d("MatchStatisticsUseCase", "matchDao.getAllMatch()")
        return matchDao.getAllMatch()
    }

    suspend fun readFromDataBase(id: String): MatchModel {
        val matchModel = MatchModel()
        val matchData: MatchModelForDataBase?
        matchData = matchDao.getById(id)
        matchModel.info.gameMode = matchData?.gameMode.toString()
        matchModel.metadata.matchId = matchData?.matchId.toString()
        return matchModel
    }
}