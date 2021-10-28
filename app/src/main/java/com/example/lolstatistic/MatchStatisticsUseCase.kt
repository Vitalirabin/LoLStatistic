package com.example.lolstatistic

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
    var mdb = db.matchesDao

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
            if (readFromDataBase(it).info?.gameMode != null) {
                matchList.add(readFromDataBase(it))
            } else matchList.add(loadMatch(it) ?: MatchModel())
        }
        return matchList
    }

    suspend fun writingDataToTheDatabase(list: MutableList<MatchModel>) {
        val matchBase = MatchModelForDataBase()
        list.forEach {
            if (readFromDataBase(it.info?.gameId.toString()).info?.gameMode == "null"){
                matchBase.gameId = it.info?.gameId.toString()
                matchBase.gameMode = it.info?.gameMode
                mdb.addData(matchBase)
            }
        }
    }

    suspend fun readFromDataBase(id: String): MatchModel {
        val matchModel = MatchModel()
        matchModel.info?.gameMode = mdb.getById(id)?.gameMode.toString()
        matchModel.info?.gameId = mdb.getById(id)?.gameId.toString()
        return matchModel
    }
}