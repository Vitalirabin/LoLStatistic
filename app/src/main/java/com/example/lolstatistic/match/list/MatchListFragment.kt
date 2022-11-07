package com.example.lolstatistic.match.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.R
import com.example.lolstatistic.match.MatchViewModel
import com.example.lolstatistic.match.details.MatchModel
import kotlinx.android.synthetic.main.fragment_match_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class MatchListFragment : BaseFragment() {

    private val matchViewModel: MatchViewModel by viewModel()
    private lateinit var adapter: MatchItemAdapter
    override fun getLayoutId(): Int = R.layout.fragment_match_list
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name=MatchListFragmentArgs.fromBundle(requireArguments()).name
        progressBar = view.findViewById(R.id.progress_bar) as ProgressBar
        matchViewModel.isLoading.value = false
        matchViewModel.getPuuidByAccount(name)
        matchViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = ProgressBar.VISIBLE
            } else {
                progressBar.visibility = ProgressBar.INVISIBLE
            }
        })
        initList(name)
        matchViewModel.listOfMatch.observe(viewLifecycleOwner, Observer {
            if (!::adapter.isInitialized) {
                adapter = MatchItemAdapter(
                    matchViewModel.puuid,
                    object : ItemOnClickListener {
                        override fun onClick(match: MatchModel?) {
                            click(match)
                        }
                    }
                )
                match_list.adapter = adapter
            }
            adapter.submitList(it)
            addOnScrollListener(name)
        })
        matchViewModel.updateList(name)
    }

    private fun addOnScrollListener(name: String) {
        match_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (totalItemCount < (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 4) {
                    matchViewModel.updateList(name)
                }
            }
        })
    }

    fun click(match: MatchModel?) {
        Log.d("MatchListFragment", "RU_${match?.info?.gameId.toString()}")
        val action = MatchListFragmentDirections.actionMatchListFragmentToMatchFragment()
        action.id = "RU_${match?.info?.gameId.toString()}"
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    private fun initList(name: String) {
    }
    //tgo
    /*   private fun addSwiped(adapter: MatchItemAdapter) {
        addOnScrollListener(name)
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
