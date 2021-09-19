package com.example.lolstatistic.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.R
import kotlinx.android.synthetic.main.item_match.view.*

class MatchItemAdapter(
    val puuid: String?,
    val onClickListener: ItemOnClickListener
) : ListAdapter<MatchModel, MatchItemAdapter.MyViewHolder>(Companion.diffCallback) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kills: TextView = itemView.match_count_of_kills
        val assists: TextView = itemView.match_count_of_assists
        val deaths: TextView = itemView.count_of_death
        val gameMode: TextView = itemView.game_mode
        val status: TextView = itemView.win_lose
        val positionItem: TextView = itemView.position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val match = getItem(position)
        holder.positionItem.text = (position + 1).toString()
        holder.kills.text =
            (match?.info?.participants?.firstOrNull { it.puuid == puuid }?.kills.toString())
        holder.assists.text =
            (match?.info?.participants?.firstOrNull { it.puuid == puuid }?.assists.toString())
        holder.deaths.text =
            (match?.info?.participants?.firstOrNull { it.puuid == puuid }?.deaths.toString())
        holder.gameMode.text = match?.info?.gameMode
        if ((match?.info?.participants?.firstOrNull { it.puuid == puuid }?.win == true)
        ) {
            holder.status.text = "Победа"
        } else holder.status.text = "Поражение"
        holder.itemView.setOnClickListener {
            onClickListener.onClick(match)
        }
    }

    companion object {
        val diffCallback = MatchDiffUtil()
    }
}