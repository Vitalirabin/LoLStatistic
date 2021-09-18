package com.example.lolstatistic.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.Constants.MATCH_ID_VALUE
import com.example.lolstatistic.R
import com.example.lolstatistic.databinding.FragmentMatchStatisticBinding
import org.koin.android.viewmodel.ext.android.viewModel
import android.util.Log

class MatchFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_match_statistic

    private val matchViewModel: MatchViewModel by viewModel()


    private var _binding: FragmentMatchStatisticBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val id =this.arguments?.getString(MATCH_ID_VALUE).toString()
        Log.d("MatchFragment", id)
        matchViewModel.getParticipant(id)
        _binding = FragmentMatchStatisticBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.matchViewModel = matchViewModel
        return binding.root
    }
}