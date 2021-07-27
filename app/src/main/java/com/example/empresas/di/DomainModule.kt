package com.example.empresas.di

import com.example.empresas.domain.core.ThreadContextProvider
import com.example.empresas.domain.usecases.*
import org.koin.dsl.module
import kotlinx.coroutines.CoroutineScope

val domainModule = module {
    single { ThreadContextProvider() }
    factory { (scope: CoroutineScope) -> LoginUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> MainUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> SplashUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> LogoutUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> FilterCompanyUseCase(scope) }
    factory { (scope: CoroutineScope) -> FavoriteCompanyUseCase(scope,get()) }

}