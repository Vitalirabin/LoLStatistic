package com.example.lolstatistic.match.list

import androidx.recyclerview.widget.DiffUtil
import com.example.lolstatistic.match.details.MatchModel


class MatchDiffUtil : DiffUtil.ItemCallback<MatchModel>() {
    override fun areItemsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean =
        oldItem.info?.gameId == newItem.info?.gameId

    override fun areContentsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
        return ( oldItem.info?.gameId == newItem.info?.gameId)
    }
}