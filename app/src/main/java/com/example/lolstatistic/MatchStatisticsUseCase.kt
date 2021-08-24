package com.example.lolstatistic

import androidx.lifecycle.MutableLiveData
import com.example.lolstatistic.account.AccountModel
import com.example.lolstatistic.account.AccountRepository
import com.example.lolstatistic.account.AccountUseCase
import com.example.lolstatistic.match.MatchModel
import com.example.lolstatistic.match.MatchRepository
import com.example.lolstatistic.statistic.MatchesModel

class MatchStatisticsUseCase(
    accountRepository: AccountRepository,
    val matchRepository: MatchRepository
) {
    val accountUseCase = AccountUseCase(accountRepository)
    var accountModel: AccountModel? = null
    var matchList: List<String>? = null
    var match: MatchModel? = null
    var matchesModel = MutableLiveData<MatchesModel>()
    var allMatches = 0
    var winMatches = 0
    var loseMatches = 0

    suspend fun getPuuid(name: String):String?{
        accountModel = accountUseCase.loadAccount(name)
        return accountModel?.puuid
    }

    suspend fun loadMatchList(puuid: String): List<String>? {
        return matchRepository.getMatchListByPuuid(puuid).data
    }

    suspend fun loadMatch(matchId: String): MatchModel? {
        return matchRepository.getMatchByMatchId(
            String.format(
                "https://europe.api.riotgames.com/lol/match/v5/matches/%s",
                matchId
            )
        ).data
    }

    suspend fun getMatchStatistic(name: String) {

        accountModel = accountUseCase.loadAccount(name)
        var startMatch = 0

        do {
            matchList = loadMatchList(
                String.format(
                    "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=%s&count=100",
                    accountModel?.puuid.toString(), startMatch.toString()
                )
            )
            startMatch++

            matchList?.forEach {
                match = loadMatch(it)
                // matchesModel.value?.allMatches=+1
                allMatches++
                if (match?.info?.participants?.get(
                        match?.metadata?.participants?.indexOf(accountModel?.puuid) ?: 0
                    )?.win == true
                ) {
                    winMatches++
                }
                //  if (match?.info!=null){
                //  matchesModel.value?.winMatches=+1}
                else //matchesModel.value?.loseMatches=+1
                    loseMatches++
            }
        } while (matchList != null)
    }
}