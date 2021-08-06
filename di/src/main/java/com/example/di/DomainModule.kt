package com.example.di

import com.example.domain.core.ThreadContextProvider
import com.example.domain.usecases.*
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    single { ThreadContextProvider() }
    factory { (scope: CoroutineScope) -> LoginUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> MainUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> SplashUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> LogoutUseCase(scope, get()) }
    factory { (scope: CoroutineScope) -> FilterCompanyUseCase(scope) }
    factory { (scope: CoroutineScope) -> FavoriteCompanyUseCase(scope,get()) }
    factory { (scope: CoroutineScope) -> ListFavoriteUseCase(scope,get()) }

}