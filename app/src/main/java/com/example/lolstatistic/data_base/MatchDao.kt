package com.example.lolstatistic.data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lolstatistic.match.details.MatchModel


@Dao
interface MatchDao {

    @Query("SELECT * FROM `match`WHERE gameId=:id")
  suspend  fun getById(id: String): MatchModelForDataBase?

    @Insert
    suspend fun addData(MMFDB:MatchModelForDataBase)


}