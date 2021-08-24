package com.example.lolstatistic.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.Constants
import com.example.lolstatistic.R
import org.koin.android.viewmodel.ext.android.viewModel

class MatchListFragment() : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_match_list

    private val matchViewModel: MatchViewModel by viewModel()


    private var layoutManager: RecyclerView.LayoutManager? = null

    //  private var adapter: RecyclerView.Adapter<MatchItemAdapter.MyViewHolder>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val bundle = arguments
        val name = bundle?.getString(Constants.USER_NAME_VALUE).toString()
        var adapter = MatchItemAdapter(
            matchViewModel.listOfMatch,
            matchViewModel.getPuuid(name)
        )
        apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
        }
    }
}