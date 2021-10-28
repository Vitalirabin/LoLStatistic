package com.example.lolstatistic.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lolstatistic.match.details.MatchModel

@Database(entities = [MatchModel::class], version = 1)
abstract class MatchDataBase : RoomDatabase() {

    abstract fun MatchesDao(): MatchDao?
}

