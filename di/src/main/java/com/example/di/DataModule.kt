package com.example.di

import com.example.data.repository_impl.EnterpriseRepositoryImpl
import com.example.data.repository_impl.LoginRepositoryImpl
import com.example.data.repository_impl.SplashRepositoryImpl
import com.example.domain.repository.EnterpriseRepository
import com.example.domain.repository.LoginRepository
import com.example.domain.repository.SplashRepository
import org.koin.dsl.module

val dataModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<EnterpriseRepository> { EnterpriseRepositoryImpl(get(),get()) }
    single<SplashRepository> { SplashRepositoryImpl(get()) }
}