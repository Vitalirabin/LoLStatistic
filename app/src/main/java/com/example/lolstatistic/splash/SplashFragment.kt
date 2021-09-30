package com.example.lolstatistic.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.lolstatistic.BaseFragment
import com.example.lolstatistic.R

class SplashFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.splash_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.postDelayed({ goToEnterFragment() }, 1500)
    }

    private fun goToEnterFragment() {
        (requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            .navigate(SplashFragmentDirections.actionSplashFragmentToEnterFragment2())
    }
}