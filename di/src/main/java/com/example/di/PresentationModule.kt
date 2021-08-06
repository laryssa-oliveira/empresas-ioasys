package com.example.di

import com.example.feature_login.presentation.LoginViewModel
import com.example.feature_main.presentation.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { DetailsViewModel() }
    viewModel { HomeViewModel() }
    viewModel { FavoriteCompaniesViewModel() }


}