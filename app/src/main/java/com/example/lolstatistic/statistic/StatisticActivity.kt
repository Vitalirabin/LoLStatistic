package com.example.lolstatistic.statistic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lolstatistic.R

class StatisticActivity : AppCompatActivity() {
    val f = StatisticFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistic_activity)
        val name = intent.getStringExtra("e")
        val bundle = Bundle()
        bundle.putString("tag", name)
        f.arguments = bundle
        pushBackStack(f)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else supportFragmentManager.popBackStack()
    }

    fun pushBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(fragment.tag)
            .add(R.id.statistic_activity_container, fragment, fragment.tag)
            .commit()
    }
}