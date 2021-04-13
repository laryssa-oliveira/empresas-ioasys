package com.example.empresas.presentation

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas.remote.CompanyService
import com.example.empresas.remote.LoginRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import okhttp3.Headers

class LoginViewModel(
        private  val service: CompanyService
): ViewModel() {
    private val _headersLiveData = MutableLiveData<Headers?>()
    val headersLiveData: LiveData<Headers?> = _headersLiveData
    fun login(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = CompanyService.newInstance().login(
                    LoginRequest(
                    email = email,
                    password = password
                )
            )
            handleLogin(response)
        }

    }

    private fun handleLogin(response: Response<Unit>) {
        if(response.isSuccessful){
            _headersLiveData.value = response.headers()
        }
    }

    fun clearStatus() {
        _headersLiveData.value = null
    }
}