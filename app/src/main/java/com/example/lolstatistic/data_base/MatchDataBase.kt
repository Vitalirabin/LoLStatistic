package com.example.lolstatistic.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lolstatistic.match.details.MatchModel

@Database(entities = arrayOf(MatchModel::class), version = 1, exportSchema = false)
abstract class MatchDataBase : RoomDatabase() {
    abstract val matchesDao: MatchDao
}

