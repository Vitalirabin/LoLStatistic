package com.example.lolstatistic

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lolstatistic.splash.SplashFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushBackStack(SplashFragment())
      }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else supportFragmentManager.popBackStack()
    }

    fun pushBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(fragment.tag)
            .add(R.id.main_container, fragment, fragment.tag)
            .commit()
    }
}