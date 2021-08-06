package com.example.feature_login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.base_feature.ViewState
import com.example.base_feature.useCase
import com.example.base_feature.viewState
import com.example.domain.usecases.LoginUseCase
import org.koin.core.KoinComponent

class LoginViewModel : ViewModel(), KoinComponent {
    private val loginUseCase: LoginUseCase by useCase()
    private val _headersLiveData by viewState<Unit>()
    val headersLiveData: LiveData<ViewState<Unit>> = _headersLiveData

    fun login(email: String, password: String) {
        _headersLiveData.value = ViewState.loading(true)
        loginUseCase(
                params = LoginUseCase.LoginParams(email, password),
                onError = {
                    _headersLiveData.value = ViewState.error(it)
                },
                onSuccess = {
                    _headersLiveData.value = ViewState.success(Unit)
                }
        )

    }

}