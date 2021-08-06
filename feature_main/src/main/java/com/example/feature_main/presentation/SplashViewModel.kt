package com.example.feature_main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.base_feature.ViewState
import com.example.base_feature.useCase
import com.example.base_feature.viewState
import com.example.domain.usecases.SplashUseCase
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