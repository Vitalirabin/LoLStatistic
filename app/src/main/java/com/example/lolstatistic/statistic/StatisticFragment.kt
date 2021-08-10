package com.example.lolstatistic.statistic


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.R
import com.example.lolstatistic.account.AccountViewModel
import com.example.lolstatistic.account.AccountViewModelFactory
import com.example.lolstatistic.databinding.FragmentStatisticsBinding
import com.example.lolstatistic.match.MatchViewModel
import com.example.lolstatistic.match.MatchViewModelFactory
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.android.viewmodel.ext.android.viewModel


class StatisticFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_statistics

    private val statisticViewModel: StatisticViewModel by viewModel()

    private val afactory = AccountViewModelFactory()
    private val accountViewModel: AccountViewModel by viewModels { afactory }

    private val mfactory = MatchViewModelFactory()
    private val matchViewModel: MatchViewModel by viewModels { mfactory }

   private var matches = MatchesModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("StatisticFragment", "onCreateView")
        val binding = DataBindingUtil.inflate<FragmentStatisticsBinding>(
            layoutInflater,
            R.layout.fragment_statistics,
            statistic_container,
            false
        )
        binding.lifecycleOwner = this
        binding.matchInfo = matches
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var i = 0
        var k = 0
        val bundle = arguments
        val name = bundle?.getString("tag").toString()

        Log.e("StatisticFragment", "onClick")
    }
}
