package com.example.empresas.injection

import com.example.empresas.data.EnterpriseRepository
import com.example.empresas.data.LoginRepository
import com.example.empresas.data.data_remote.enterprise.EnterpriseRepositoryImpl
import com.example.empresas.data.data_remote.login.LoginRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get(),get()) }
    single<EnterpriseRepository> { EnterpriseRepositoryImpl(get(),get()) }
}