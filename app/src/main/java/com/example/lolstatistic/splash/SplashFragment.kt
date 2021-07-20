package com.example.lolstatistic.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.R
import com.example.lolstatistic.enter.EnterActivity

class SplashFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.splash_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.postDelayed({
            val intent = Intent(context, EnterActivity::class.java)
            startActivity(intent)
        }, 1500)
    }
}