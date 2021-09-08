package com.example.lolstatistic.match

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.R
import kotlinx.android.synthetic.main.item_match.view.*
import kotlin.coroutines.coroutineContext

class MatchItemAdapter(
    private val context: Context,
    private val matches: List<MatchModel?>,
    val puuid: String?,
    val onClickListener: ItemOnClickListener
) : RecyclerView.Adapter<MatchItemAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val kills: TextView = itemView.count_of_kills
        val assists: TextView = itemView.count_of_assists
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

    override fun getItemCount() = matches.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val match = matches[position]
        holder.positionItem.text = (position + 1).toString()
        holder.kills.text = (match?.info?.participants?.get(
            match.metadata?.participants?.indexOf(puuid) ?: 0
        )?.kills.toString())
        holder.assists.text = (match?.info?.participants?.get(
            match.metadata?.participants?.indexOf(puuid) ?: 0
        )?.assists.toString())
        holder.deaths.text = (match?.info?.participants?.get(
            match.metadata?.participants?.indexOf(puuid) ?: 0
        )?.deaths.toString())
        holder.gameMode.text = match?.info?.gameMode
        if ((match?.info?.participants?.get(
                match.metadata?.participants?.indexOf(puuid) ?: 0
            )?.win == true)
        ) {
            holder.status.text = "Победа"
        } else holder.status.text = "Поражение"
        holder.itemView.setOnClickListener {
            onClickListener.onClick(match)
        }
    }
}