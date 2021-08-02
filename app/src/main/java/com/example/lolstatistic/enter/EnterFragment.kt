package com.example.lolstatistic.enter

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.viewModels
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.MainActivity
import com.example.lolstatistic.R
import com.example.lolstatistic.statistic.StatisticFragment
import kotlinx.android.synthetic.main.fragment_enter_name.*
import java.util.*

class EnterFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_enter_name

    private val factory = EnterViewModelFactory()

    private val enterViewModel: EnterViewModel by viewModels { factory }

    var name = view?.findViewById<EditText>(R.id.name_of_account)?.text.toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list: MutableList<String> = mutableListOf()
        list.add("BR1")
        list.add("EUN1")
        list.add("EUW1")
        list.add("JP1")
        list.add("KR")
        list.add("LA1")
        list.add("LA2")
        list.add("NA1")
        list.add("OC1")
        list.add("OC1")
        list.add("Ru")
        list.add("TR1")
        val spinner = view.findViewById<Spinner>(R.id.spinner);
        val adapter = ArrayAdapter<String>(
            (requireActivity() as MainActivity),
            R.layout.server_spinner,
            list
        )
        adapter.setDropDownViewResource(R.layout.server_spiner_dropdown)
        spinner.adapter = adapter
        spinner.prompt = "Выберите сервеер"
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        view.findViewById<Button>(R.id.enter_button).setOnClickListener {
            if (view.findViewById<EditText>(R.id.name_of_account).text == null) {
                Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val f = StatisticFragment()
            val bundle = Bundle()
            bundle.putString("tag", view.findViewById<EditText>(R.id.name_of_account).text.toString())
            f.arguments = bundle
            (requireActivity() as MainActivity).pushBackStack(f)
        }
    }

}