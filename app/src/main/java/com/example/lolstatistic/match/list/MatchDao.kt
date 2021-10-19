package com.example.lolstatistic.match.list

import com.example.lolstatistic.match.details.MatchModel


@MatchDao.Dao
interface MatchDao {
    annotation class Dao
    @Query("SELECT * FROM game")
    fun getAll(): List<MatchModel?>?

    annotation class Query(val value: String)

    @Query("SELECT * FROM game id")
    fun getById(id: String): MatchModel?

    @Insert
    fun updateData(list: MutableList<MatchModel>?)

    annotation class Insert

    @Delete
    fun deleteMatch(id: String)

    annotation class Delete
}