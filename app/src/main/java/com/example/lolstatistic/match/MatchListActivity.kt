package com.example.lolstatistic.match

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.Constants.USER_NAME_VALUE
import com.example.lolstatistic.R
import kotlinx.android.synthetic.main.activity_match_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MatchListActivity : AppCompatActivity() {

    private val matchViewModel: MatchViewModel by viewModel()
    private var isLoading: Boolean = false
    private lateinit var layoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_list)
        val arguments: Bundle? = intent.extras
        val name = arguments?.get(USER_NAME_VALUE).toString()
        match_list.setHasFixedSize(true)
        matchViewModel.muListOfMatch.observe(this, Observer {
            val adapter = MatchItemAdapter(
                this@MatchListActivity,
                it ?: emptyList<MatchModel>(),
                matchViewModel.getPuuid(name)
            )
            adapter.notifyDataSetChanged()
            match_list.adapter = adapter
            match_list.layoutManager = LinearLayoutManager(this@MatchListActivity)
           /* match_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!isLoading) {
                        if (layoutManager.findLastCompletelyVisibleItemPosition() == (matchViewModel.muListOfMatch.value?.size?:0) - 1) {
                            matchViewModel.updateList(name)
                            isLoading = true
                        }
                    }
                }
            })*/

        })
        matchViewModel.loadMatchList(name)
    }
}
