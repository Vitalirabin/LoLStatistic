package com.example.lolstatistic.match

import android.util.Log
import com.example.lolstatistic.data_base.MatchDataBase
import com.example.lolstatistic.data_base.MatchModelForDataBase
import com.example.lolstatistic.match.details.MatchModel
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class MatchRepository(private var api: RemoteApi, db: MatchDataBase) {
    private var matchDao = db.matchesDao
    suspend fun getMatchListByPuuid(puuid: String): ApiResponse<List<String>> {
        return try {
            api = ApiFactory.getApi()
            val result = api.getMatchListByPuuid(puuid)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getMatchListByPuuid", e.toString())
            ApiResponse(null, e)
        }
    }

    suspend fun getMatchByMatchId(matchId: String): ApiResponse<MatchModel> {
        return try {
            api = ApiFactory.getApi()
            val result = api.getMatch(matchId)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getMatchByMatchId", e.toString())
            ApiResponse(null, e)
        }
    }

    suspend fun getMatchByDB(id: String): MatchModelForDataBase? {
        return matchDao.getById(id)
    }

    suspend fun getAllMatchFromDB(): List<MatchModelForDataBase> {
        return matchDao.getAllMatch()
    }

    suspend fun loadMatch(matchId: String, puuid: String): MatchModel? {
        Log.d("MatchStatisticsUseCase", "loadMatch")
        if (readFromDataBase(matchId, puuid).info.gameId == null) {
            val match = getMatchByMatchId(
                String.format(
                    "https://europe.api.riotgames.com/lol/match/v5/matches/%s",
                    matchId
                )
            ).data
            writingMatchToTheDataBase(puuid, match ?: MatchModel())
            return match
        } else return readFromDataBase(matchId, puuid)
    }

    suspend fun writingMatchToTheDataBase(puuid: String, match: MatchModel) {
        Log.d("MatchStatisticsUseCase", "writingMatchToTheDataBase")
        val matchBase = MatchModelForDataBase()
        matchBase.matchId = match.metadata.matchId.toString()
        matchBase.gameMode = match.info.gameMode
        matchBase.puuid0 = puuid
        matchBase.assists0 = match.info.participants?.firstOrNull { it.puuid == puuid }?.assists
        matchBase.deaths0 = match.info.participants?.firstOrNull { it.puuid == puuid }?.deaths
        matchBase.kills0 = match.info.participants?.firstOrNull { it.puuid == puuid }?.kills
        matchBase.win0 = match.info.participants?.firstOrNull { it.puuid == puuid }?.win ?: true
        matchDao.addData(matchBase)
    }

    suspend fun readFromDataBase(id: String, puuid: String): MatchModel {
        val matchModel = MatchModel()
        val matchData: MatchModelForDataBase?
        matchData = matchDao.getById(id)
        matchModel.info.gameMode = matchData?.gameMode.toString()
        matchModel.metadata.matchId = matchData?.matchId.toString()
        matchModel.info.participants?.firstOrNull { it.puuid == puuid }?.deaths = matchData?.deaths0
        matchModel.info.participants?.firstOrNull { it.puuid == puuid }?.assists =
            matchData?.assists0
        matchModel.info.participants?.firstOrNull { it.puuid == puuid }?.kills = matchData?.kills0
        matchModel.info.participants?.firstOrNull { it.puuid == puuid }?.win =
            matchData?.win0 ?: true
        return matchModel
    }
}