package com.example.empresas.injection

import com.example.empresas.data.data_remote.enterprise.CompanyService
import com.example.empresas.data.data_remote.login.LoginService
import org.koin.dsl.module

val dataRemoteModule = module {

    single { CompanyService.newInstance() }
    single { LoginService.newInstance() }
}