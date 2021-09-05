package com.example.lolstatistic.network

import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.match.MatchIdModel
import com.example.lolstatistic.match.MatchModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url

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
        "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
        "Accept-Charset: application/x-www-form-urlencoded; charset=UTF-8",
        "X-Riot-Token:RGAPI-957f1dbb-ad0f-4e56-8559-5d6164598bd0"
    )

    @GET("/lol/summoner/v4/summoners/by-name/{name}")
    suspend fun getAccountByName(@Path("name") name: String): AccountModel

    @Headers(
        "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
        "Accept-Charset: application/x-www-form-urlencoded; charset=UTF-8",
        "X-Riot-Token:RGAPI-957f1dbb-ad0f-4e56-8559-5d6164598bd0"
    )

    @GET
    suspend fun getMatchListByPuuid(@Url url: String): List<String>

    @Headers(
        "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
        "Accept-Charset: application/x-www-form-urlencoded; charset=UTF-8",
        "X-Riot-Token:RGAPI-957f1dbb-ad0f-4e56-8559-5d6164598bd0"
    )
    @GET
    suspend fun getMatch(@Url url: String): MatchModel
}