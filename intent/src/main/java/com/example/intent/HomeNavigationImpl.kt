package com.example.intent

import androidx.fragment.app.Fragment
import com.example.domain.entities.Company
import com.example.feature_main.navigation.HomeNavigation
import com.example.feature_main.ui.HomeFragmentDirections
import com.example.intent.utils.navigate

class HomeNavigationImpl(private val fragment: Fragment) : HomeNavigation {
    override fun navigateToDetails(company: Company) = fragment.navigate(
        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(company)
    )

    override fun navigateToLogin() = fragment.navigate(
        HomeFragmentDirections.actionHomeFragmentToLoginFragment()
    )
}