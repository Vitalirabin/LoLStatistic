package com.example.lolstatistic.match

import android.util.Log
import com.example.lolstatistic.match.details.MatchModel
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class MatchRepository(private var api: RemoteApi) {
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

    suspend fun loadMatch(matchId: String, puuid: String): MatchModel {
        Log.d("MatchRepository", "loadMatch")
        val match = getMatchByMatchId(
            String.format(
                "https://europe.api.riotgames.com/lol/match/v5/matches/%s",
                matchId
            )
        ).data
        return match ?: MatchModel()
    }
}