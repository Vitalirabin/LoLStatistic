package com.example.lolstatistic.network

import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.statistic.StatisticModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface RemoteApi {
    /*  @GET("/id")
      fun getInfo(): Call<StatisticModel>
      @GET("br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getBR1(): Call<StatisticModel>
      @GET("eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getEUN1(): Call<StatisticModel>
      @GET("euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getEUW1(): Call<StatisticModel>
      @GET("jp1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getJP1(): Call<StatisticModel>
      @GET("kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getKR(): Call<StatisticModel>
      @GET("la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getLA1(): Call<StatisticModel>
      @GET("la2.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getLA2(): Call<StatisticModel>
      @GET("na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getNA1(): Call<StatisticModel>
      @GET("oc1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getOC1(): Call<StatisticModel>
      @GET("ru.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getRU(): Call<StatisticModel>
      @GET("tr1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
      fun getTR1(): Call<StatisticModel>*/
    @Headers(
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36 OPR/76.0.4017.227",
        "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
        "Accept-Charset: application/x-www-form-urlencoded; charset=UTF-8",
        "Origin: https://developer.riotgames.com",
        "X-Riot-Token:RGAPI-6d0eedd5-fcf5-4eca-9e7f-2a34564d7ef5"
    )

    @GET("/lol/summoner/v4/summoners/by-name/{id}")
    suspend fun getAccount(id: String): AccountModel


    @GET("/lol/match/v4/matchlists/by-account/{accountId}")
    suspend fun getMatchListByPuuid(accountId: String): List<String>

    @GET("/lol/match/v5/matches/{matchId}")
    suspend fun getMatch(matchId: String): MatchModel
}