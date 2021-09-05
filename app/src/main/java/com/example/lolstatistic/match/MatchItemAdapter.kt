package com.example.lolstatistic.match

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.R
import kotlinx.android.synthetic.main.item_match.view.*

class MatchItemAdapter(   private val context: Context,   private val matches: List<MatchModel?>, val puuid: String?) :  RecyclerView.Adapter<MatchItemAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kills: TextView = itemView.count_of_kills
        val assists: TextView = itemView.count_of_assists
        val deaths: TextView = itemView.count_of_death
        val gameMode: TextView = itemView.game_mode
        val status: TextView = itemView.win_lose
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount() = matches.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val match = matches[position]

        holder.kills.text = (match?.info?.participants?.get(
            match.metadata?.participants?.indexOf(puuid) ?: 0
        )?.kills.toString())
        holder.assists.text = (match?.info?.participants?.get(
            match.metadata?.participants?.indexOf(puuid) ?: 0
        )?.assists.toString())
        holder.deaths.text = (match?.info?.participants?.get(
            match.metadata?.participants?.indexOf(puuid) ?: 0
        )?.deaths.toString())
        holder.gameMode.text = match?.info?.gameMod
        holder.status.text = (match?.info?.participants?.get(
            match.metadata?.participants?.indexOf(puuid) ?: 0
        )?.win.toString())
    }

}