package com.example.lolstatistic.match.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.R
import com.example.lolstatistic.match.details.MatchModel
import kotlinx.android.synthetic.main.item_match.view.*

val diffCallback = MatchDiffUtil()

class MatchItemAdapter(
    val puuid: String?,
    val onClickListener: ItemOnClickListener
) : ListAdapter<MatchModel, MatchItemAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kills: TextView = itemView.match_count_of_kills
        val assists: TextView = itemView.match_count_of_assists
        val deaths: TextView = itemView.count_of_death
        val gameMode: TextView = itemView.game_mode
        val status: TextView = itemView.win_lose
        val positionItem: TextView = itemView.position
        val matchId: TextView = itemView.count_of_match_id

        fun bind(
            match: MatchModel,
            onClickListener: ItemOnClickListener,
            puuid: String?
        ) {
            this.matchId.text = match.metadata?.matchId.toString()
            this.positionItem.text = (position + 1).toString()
            this.kills.text =
                (match?.info?.participants?.firstOrNull { it.puuid == puuid }?.kills.toString())
            this.assists.text =
                (match?.info?.participants?.firstOrNull { it.puuid == puuid }?.assists.toString())
            this.deaths.text =
                (match?.info?.participants?.firstOrNull { it.puuid == puuid }?.deaths.toString())
            this.gameMode.text = match?.info?.gameMode
            if ((match?.info?.participants?.firstOrNull { it.puuid == puuid }?.win == true)
            ) {
                this.status.text = "Победа"
            } else this.status.text = "Поражение"
            this.itemView.setOnClickListener {
                onClickListener.onClick(match)
            }
        }
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
        holder.bind(match, onClickListener, puuid)
    }
}