package com.example.lolstatistic.match

import android.util.Log
import com.example.lolstatistic.data_base.MatchDataBase
import com.example.lolstatistic.data_base.MatchModelForDataBase
import com.example.lolstatistic.match.details.MatchModel
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class MatchRepository(var api: RemoteApi,   db: MatchDataBase) {
    var matchDao = db.matchesDao
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

    suspend fun getMatchByDB(id: String):MatchModelForDataBase?{
        return matchDao.getById(id)
    }

    suspend fun getAllMatchFromDB( ):List<MatchModelForDataBase>{
        return matchDao.getAllMatch()
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        Log.d("MatchStatisticsUseCase", "loadMatch")
        if (readFromDataBase(matchId).info.gameId == null) {
            val match = getMatchByMatchId(
                String.format(
                    "https://europe.api.riotgames.com/lol/match/v5/matches/%s",
                    matchId
                )
            ).data
            writingMatchToTheDataBase(match ?: MatchModel())
            return match
        } else return readFromDataBase(matchId)
    }

    suspend fun writingMatchToTheDataBase(match: MatchModel) {
        Log.d("MatchStatisticsUseCase", "writingMatchToTheDataBase")
        val matchBase = MatchModelForDataBase()
        matchBase.matchId = match.metadata.matchId.toString()
        matchBase.gameMode = match.info.gameMode
        matchDao.addData(matchBase)
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