package com.example.lolstatistic.match

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lolstatistic.Constants.USER_NAME_VALUE
import com.example.lolstatistic.R
import kotlinx.android.synthetic.main.activity_match_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MatchListActivity : AppCompatActivity() {

    private val matchViewModel: MatchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_list)
        val arguments:Bundle?=intent.extras
        val name=arguments?.get(USER_NAME_VALUE).toString()
        val puuid = matchViewModel.getPuuid(name)
        match_list.setHasFixedSize(true)
        matchViewModel.muListOfMatch.observe(this, Observer {
            val adapter = MatchItemAdapter(
                this@MatchListActivity,
                it ?: Collections.emptyList<MatchModel>(),
                puuid
            )
            adapter.notifyDataSetChanged()
            match_list.adapter = adapter
            match_list.layoutManager = LinearLayoutManager(this@MatchListActivity)
        })
        matchViewModel.loadMatchList(name)
    }
}
