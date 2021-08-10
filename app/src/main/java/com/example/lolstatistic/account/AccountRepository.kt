package com.example.lolstatistic.account

import android.util.Log
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi
import retrofit2.await

class AccountRepository(
    var api: RemoteApi
) {
    suspend fun getAccount(id:String): ApiResponse<AccountModel> {
        return try {
            api = ApiFactory.getApi()
            val result = api.getAccount(id)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getAllMoveList",e.toString())
            ApiResponse(null, e)
        }
    }
}

