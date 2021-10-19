package com.example.lolstatistic.match.details

class InfoOfMatch {
    val participants: List<Participant>? = null
    val gameMode: String? = null
    @PrimaryKey  val gameId: String? = null

    annotation class PrimaryKey
}

