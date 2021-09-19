package com.example.lolstatistic.match

import androidx.recyclerview.widget.DiffUtil


class MatchDiffUtil : DiffUtil.ItemCallback<MatchModel>() {
    override fun areItemsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean =
        oldItem.info?.gameId == newItem.info?.gameId

    override fun areContentsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
        return ( oldItem.info?.gameId == newItem.info?.gameId)
    }
}