package com.example.lolstatistic.data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MatchDao {

    @Query("SELECT * FROM `match`WHERE matchId=:id")
    suspend fun getById(id: String): MatchModelForDataBase?

    @Query("SELECT * FROM `match`")
    suspend fun getAllMatch(): List<MatchModelForDataBase>

    @Insert
    suspend fun addData(MMFDB: MatchModelForDataBase)
}