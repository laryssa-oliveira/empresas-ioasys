package com.example.empresas.di

import com.example.empresas.domain.repository.EnterpriseRepository
import com.example.empresas.domain.repository.LoginRepository
import com.example.empresas.data.repository_impl.EnterpriseRepositoryImpl
import com.example.empresas.data.repository_impl.LoginRepositoryImpl
import com.example.empresas.data.repository_impl.SplashRepositoryImpl
import com.example.empresas.domain.repository.SplashRepository
import org.koin.dsl.module

val dataModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get(),get()) }
    single<EnterpriseRepository> { EnterpriseRepositoryImpl(get(),get()) }
    single<SplashRepository> { SplashRepositoryImpl(get()) }
}