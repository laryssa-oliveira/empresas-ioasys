package com.example.empresas.injection

import com.example.empresas.presentation.LoginViewModel
import com.example.empresas.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { MainViewModel(get()) }
}