package com.example.lolstatistic.enter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lolstatistic.R
import com.example.lolstatistic.splash.SplashFragment

class EnterActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_activity)
        pushBackStack(EnterFragment())
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
        val spinner = findViewById<Spinner>(R.id.spinner);
        val adapter = ArrayAdapter<String>(
            this,
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
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else supportFragmentManager.popBackStack()
    }

    fun pushBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(fragment.tag)
            .add(R.id.enter_container, fragment, fragment.tag)
            .commit()
    }
}