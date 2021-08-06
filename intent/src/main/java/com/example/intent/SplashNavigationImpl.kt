package com.example.intent

import androidx.fragment.app.Fragment
import com.example.feature_main.navigation.SplashNavigation
import com.example.feature_main.ui.SplashFragmentDirections
import com.example.intent.utils.navigate

class SplashNavigationImpl(private val fragment: Fragment) : SplashNavigation {

    override fun navigateToHome() = fragment.navigate(
        SplashFragmentDirections.actionSplashFragmentToHomeFragment()
    )

    override fun navigateToLogin() = fragment.navigate(
        SplashFragmentDirections.actionSplashFragmentToLoginFragment()
    )
}