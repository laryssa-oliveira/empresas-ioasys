package com.example.feature_main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.base_feature.ViewState
import com.example.base_feature.useCase
import com.example.base_feature.viewState
import com.example.domain.usecases.LogoutUseCase
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