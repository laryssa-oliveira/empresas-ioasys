package com.example.feature_main.navigation

import com.example.domain.entities.Company

interface HomeNavigation {
    fun navigateToDetails(company: Company)
    fun navigateToLogin()
}