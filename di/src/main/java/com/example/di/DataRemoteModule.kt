package com.example.di

import com.example.data.datasource.EnterpriseRemoteDataSource
import com.example.data.datasource.LoginRemoteDataSource
import com.example.data_remote.datasource.EnterpriseRemoteDataSourceImpl
import com.example.data_remote.datasource.LoginRemoteDataSourceImpl
import com.example.data_remote.enterprise.api.CompanyService
import com.example.data_remote.login.api.LoginService
import org.koin.dsl.module

val dataRemoteModule = module {

    single { CompanyService.newInstance() }
    single { LoginService.newInstance() }
    single<LoginRemoteDataSource> { LoginRemoteDataSourceImpl(get(),get()) }
    single<EnterpriseRemoteDataSource> { EnterpriseRemoteDataSourceImpl(get()) }
}