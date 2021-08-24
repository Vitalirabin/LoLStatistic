package com.example.lolstatistic.account

import android.util.Log
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class AccountRepository(
    var api: RemoteApi
) {
    suspend fun getAccount(name:String): ApiResponse<AccountModel> {
        return try {
            api = ApiFactory.getApi()
            val result = api.getAccountByName(name)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getAccount",e.toString())
            ApiResponse(null, e)
        }
    }
}

