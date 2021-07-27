package com.example.empresas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.empresas.domain.core.useCase
import com.example.empresas.domain.usecases.LogoutUseCase
import com.example.empresas.extensions.viewState
import org.koin.core.KoinComponent

class HomeViewModel : ViewModel(), KoinComponent {

    private val logoutUseCase: LogoutUseCase by useCase()
    private val _logoutLiveData by viewState<Unit>()
    val logoutLiveData: LiveData<ViewState<Unit>> = _logoutLiveData

    fun logout() {
        _logoutLiveData.value = ViewState.loading(true)
        logoutUseCase(
            onError = {
                _logoutLiveData.value = ViewState.error(it)
                _logoutLiveData.value = ViewState.loading(false)
            },
            onSuccess = {
                _logoutLiveData.value = ViewState.success(it)
                _logoutLiveData.value = ViewState.loading(false)
            }
        )
    }
}