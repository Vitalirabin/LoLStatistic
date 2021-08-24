package com.example.lolstatistic.enter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.Constants.USER_NAME_VALUE
import com.example.lolstatistic.MainActivity
import com.example.lolstatistic.R
import com.example.lolstatistic.databinding.FragmentEnterNameBinding
import com.example.lolstatistic.match.MatchListFragment
import com.example.lolstatistic.statistic.StatisticFragment
import kotlinx.android.synthetic.main.fragment_enter_name.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class EnterFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_enter_name

    private val enterViewModel: EnterViewModel by viewModel()

    var name = view?.findViewById<EditText>(R.id.name_of_account)?.text.toString()

    private var _binding: FragmentEnterNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterNameBinding.inflate(inflater, container, false)
        binding.lifecycleOwner=this
        binding.clickInfo=enterViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createSpinner()
       /* view.findViewById<Button>(R.id.enter_button).setOnClickListener {
            if (view.findViewById<EditText>(R.id.name_of_account).text == null) {
                Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val statisticFragment = StatisticFragment()
            val bundle = Bundle()
            bundle.putString(
                USER_NAME_VALUE,
                view.findViewById<EditText>(R.id.name_of_account).text.toString()
            )
            statisticFragment.arguments = bundle
            (requireActivity() as MainActivity).pushBackStack(statisticFragment)
        }*/
        view.findViewById<Button>(R.id.enter_button).setOnClickListener {
            if (view.findViewById<EditText>(R.id.name_of_account).text == null) {
                Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val matchListFragment = MatchListFragment()
            val bundle = Bundle()
            bundle.putString(
                USER_NAME_VALUE,
                view.findViewById<EditText>(R.id.name_of_account).text.toString()
            )
            matchListFragment.arguments = bundle
            (requireActivity() as MainActivity).pushBackStack(matchListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createSpinner() {
        val adapter = ArrayAdapter<String>(
            (requireActivity() as MainActivity),
            R.layout.server_spinner,
            Servers.values().map { it.server }
        )
        adapter.setDropDownViewResource(R.layout.server_spiner_dropdown)
        binding.spinner.adapter = adapter
        binding.spinner.prompt = "Выберите сервер"
        binding.spinner.setSelection(0)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

}