package com.example.lolstatistic.match

import androidx.recyclerview.widget.DiffUtil


class MatchDiffUtil(private val oldList: List<MatchModel?>, private val newList: List<MatchModel?>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMatch: MatchModel? = oldList[oldItemPosition]
        val newMatch: MatchModel? = newList[newItemPosition]
        return oldMatch?.info?.gameId == newMatch?.info?.gameId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMatch: MatchModel? = oldList[oldItemPosition]
        val newMatch: MatchModel? = newList[newItemPosition]
        return (oldMatch?.info?.gameMode==newMatch?.info?.gameMode
                && oldMatch?.metadata?.participants == newMatch?.metadata?.participants)
    }

}