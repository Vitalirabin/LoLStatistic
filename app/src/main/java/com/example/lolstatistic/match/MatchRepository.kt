package com.example.lolstatistic.match

import android.util.Log
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class MatchRepository(var api: RemoteApi) {
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
}