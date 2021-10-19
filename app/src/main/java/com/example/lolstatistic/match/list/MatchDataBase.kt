package com.example.lolstatistic.match.list

import androidx.room.RoomDatabase
import com.example.lolstatistic.match.details.Participant
import kotlin.reflect.KClass

@MatchDataBase.Database(participant = arrayOf(Participant::class), version = 1)
abstract class MatchDataBase : RoomDatabase() {
    annotation class Database(val participant: Array<KClass<Participant>>, val version: Int)

    abstract fun MatchesDao(): MatchDao?
}

