package com.example.lolstatistic.match

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.Constants.USER_NAME_VALUE
import com.example.lolstatistic.R
import kotlinx.android.synthetic.main.activity_match_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MatchListActivity : AppCompatActivity() {

    private val matchViewModel: MatchViewModel by viewModel()
    private var isLoading: Boolean = true
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_list)
        val arguments: Bundle? = intent.extras
        val name = arguments?.get(USER_NAME_VALUE).toString()
        match_list.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        matchViewModel.muListOfMatch.observe(this, Observer {
            val adapter = MatchItemAdapter(
                this@MatchListActivity,
                it ?: emptyList<MatchModel>(),
                matchViewModel.getPuuid(),
                object : ItemOnClickListener {
                    override fun onClick(match: MatchModel?) {
                        Log.d("MatchListActivity","ONCLICK!!")
                        Toast.makeText(
                            this@MatchListActivity,
                            match?.info?.gameMode.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            )
            adapter.notifyDataSetChanged()
            match_list.adapter = adapter
            match_list.layoutManager = layoutManager
            addOnScrollListener(name)
        })
        matchViewModel.loadMatchList(name)
    }

    private fun addOnScrollListener(name: String) {
        match_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (isLoading && totalItemCount == layoutManager.findLastVisibleItemPosition() + 1) {
                    matchViewModel.updateList(name)
                    isLoading = true
                }
            }
        })
    }

 /*   private fun addSwiped(adapter: MatchItemAdapter) {
        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                match_list.removeItemDecorationAt(position)
                adapter.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(match_list)
    }*/
}
