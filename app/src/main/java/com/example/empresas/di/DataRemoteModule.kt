package com.example.empresas.di

import com.example.empresas.data.remote_data.enterprise.api.CompanyService
import com.example.empresas.data.remote_data.login.api.LoginService
import org.koin.dsl.module

val dataRemoteModule = module {

    single { CompanyService.newInstance() }
    single { LoginService.newInstance() }
}