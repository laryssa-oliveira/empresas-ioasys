package com.example.empresas.di

import com.example.empresas.presentation.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { DetailsViewModel() }
    viewModel { HomeViewModel() }


}