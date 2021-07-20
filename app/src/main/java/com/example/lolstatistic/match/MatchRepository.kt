package com.example.lolstatistic.match

import android.util.Log
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class MatchRepository(
    var api: RemoteApi
) {
    suspend fun getAllMovieList(matchId: String): ApiResponse<MatchModel> {
        return try {
            api = ApiFactory.getApi()
            val result = api.getMatch(matchId)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getAllMoveList", e.toString())
            ApiResponse(null, e)
        }
    }
}