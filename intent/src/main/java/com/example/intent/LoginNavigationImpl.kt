package com.example.intent

import androidx.fragment.app.Fragment
import com.example.feature_login.navigation.LoginNavigation
import com.example.feature_login.ui.LoginFragmentDirections
import com.example.intent.utils.navigate

class LoginNavigationImpl(private val fragment: Fragment): LoginNavigation {
    override fun navigateToHome() = fragment.navigate(
        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
    )
}