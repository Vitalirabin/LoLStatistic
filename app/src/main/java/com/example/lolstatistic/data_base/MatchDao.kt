package com.example.lolstatistic.data_base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lolstatistic.match.details.MatchModel


@MatchDao.Dao
interface MatchDao {
    annotation class Dao

    @Query("SELECT * FROM `match`")
    fun getAll(): MutableList<MatchModel>

    @Query("SELECT * FROM `match`WHERE gameId=:id")
    fun getById(id: String): MatchModel?

    @Insert
    fun updateData(MMFDB:MatchModelForDataBase)

    @Delete
    fun deleteMatch(id: String)

    @Delete
    fun delete()
}