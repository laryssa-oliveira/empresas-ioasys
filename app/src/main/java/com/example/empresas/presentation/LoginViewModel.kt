package com.example.empresas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empresas.extensions.viewState
import com.example.empresas.data.remote_data.core.ResultWrapper
import com.example.empresas.domain.core.useCase
import com.example.empresas.domain.repository.LoginRepository
import com.example.empresas.domain.usecases.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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