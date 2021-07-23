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


class StatisticFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_statistics
    private val sfactory = StatisticViewModelFactory()
    val statisticViewModel: StatisticViewModel by viewModels { sfactory }
    private val afactory = AccountViewModelFactory()
    private val accountViewModel: AccountViewModel by viewModels { afactory }
    private val mfactory = MatchViewModelFactory()
    private val matchViewModel: MatchViewModel by viewModels { mfactory }
    var matches = MatchesModel()
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
        accountViewModel.loadAccount(name)
        statisticViewModel.loadMatchId(accountViewModel.accountModel.value?.puuid.toString())
        statisticViewModel.statisticModel.value?.forEach {
            matches.allMatches++
            matchViewModel.loadMatch(it)
            matchViewModel.matchModel.value?.metadata?.participants?.forEach {
                if (it == accountViewModel.accountModel.value?.puuid.toString()) {
                    k = i
                }
                i++
            }
            if (matchViewModel.matchModel.value?.info?.participants?.get(k)?.win == true) {
                matches.winMatches++
            } else matches.loseMatches++
        }
        Log.e("StatisticFragment", "onClick")
    }
}
