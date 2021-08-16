package com.example.lolstatistic.statistic


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.Constants.USER_NAME_VALUE
import com.example.lolstatistic.MainActivity
import com.example.lolstatistic.R
import com.example.lolstatistic.databinding.FragmentStatisticsBinding
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.android.viewmodel.ext.android.viewModel


class StatisticFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_statistics

    private val statisticViewModel: StatisticViewModel by viewModel()


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
        binding.matchInfo = statisticViewModel.matchesModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val name = bundle?.getString(USER_NAME_VALUE).toString()
        statisticViewModel.getMatchStatistic(name)
        view.findViewById<Button>(R.id.name_again).setOnClickListener {
            (requireActivity() as MainActivity).onBackPressed()
        }
    }
}
