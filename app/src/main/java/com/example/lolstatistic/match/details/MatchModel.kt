package com.example.lolstatistic.match.details

import androidx.room.ColumnInfo

@Entity
class MatchModel {
    @ColumnInfo(name = "metadata")
    val metadata: DataMatch? = null

    @ColumnInfo(name = "info")
    val info: InfoOfMatch? = null
}

annotation class Entity
