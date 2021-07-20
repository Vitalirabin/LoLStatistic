package com.example.lolstatistic.enter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.viewModels
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.R
import com.example.lolstatistic.statistic.StatisticActivity
import kotlinx.android.synthetic.main.enter_activity.*
import kotlinx.android.synthetic.main.fragment_enter_name.*
import java.util.*

class EnterFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_enter_name
    private val factory = EnterViewModelFactory()
    private val enterViewModel: EnterViewModel by viewModels { factory }
    var name = view?.findViewById<EditText>(R.id.name_of_account)?.text.toString()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.enter_button).setOnClickListener {
            if (view.findViewById<EditText>(R.id.name_of_account).text == null) {
                Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val intent = Intent(context, StatisticActivity::class.java)
            intent.putExtra(
                "e",
                "Crazy Father"
            )
            startActivity(intent)
        }
    }

}