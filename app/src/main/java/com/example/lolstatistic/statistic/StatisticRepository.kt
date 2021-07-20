package com.example.lolstatistic.statistic

import android.util.Log
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class StatisticRepository(
    var api: RemoteApi
) {
    suspend fun getAllMovieList(accountId:String): ApiResponse<List<String>> {
        return try {
            api = ApiFactory.getApi()
            val result = api.getMatchIdList(accountId)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getAllMoveList",e.toString())
            ApiResponse(null, e)
        }
    }
}