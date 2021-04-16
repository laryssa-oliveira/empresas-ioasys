package com.example.empresas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empresas.extensions.viewState
import com.example.empresas.data.data_remote.ResultWrapper
import com.example.empresas.data.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
        private val repository : LoginRepository
): ViewModel(){

    private val _headersLiveData by viewState<Unit>()
    val headersLiveData: LiveData<ViewState<Unit>> = _headersLiveData

    fun login(email: String, password: String) {
        _headersLiveData.value = ViewState.loading(true)
        viewModelScope.launch(Dispatchers.Main) {
            handleLogin(repository.login(email, password))
        }

    }

    private fun handleLogin(response: ResultWrapper<Unit>) {
        when(response){
            is ResultWrapper.Success -> _headersLiveData.value = ViewState.success(Unit)
            is ResultWrapper.Failure -> _headersLiveData.value = ViewState.error(response.error)
        }
        _headersLiveData.value = ViewState.loading(false)
    }

    fun clearStatus() {
        _headersLiveData.value = null
    }
}