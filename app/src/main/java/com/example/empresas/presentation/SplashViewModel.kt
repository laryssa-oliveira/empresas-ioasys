package com.example.empresas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.empresas.domain.core.useCase
import com.example.empresas.domain.usecases.SplashUseCase
import com.example.empresas.extensions.viewState
import org.koin.core.KoinComponent

class SplashViewModel: ViewModel(), KoinComponent {
    private val splashUseCase: SplashUseCase by useCase()
    private val _isLoggedViewState by viewState<Boolean>()
    val isLoggedViewState: LiveData<ViewState<Boolean>> = _isLoggedViewState

    fun isLogged(){
        splashUseCase(
            onSuccess = {_isLoggedViewState.value = ViewState.success (it)},
            onError = {_isLoggedViewState.value = ViewState.error (it)}

        )
    }
}