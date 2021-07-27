package com.example.empresas.domain.usecases

import com.example.empresas.data.remote_data.core.ResultWrapper
import com.example.empresas.domain.core.UseCase
import com.example.empresas.domain.exceptions.EmptyFieldException
import com.example.empresas.domain.exceptions.MissingParamsException
import com.example.empresas.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LoginUseCase(scope: CoroutineScope, private val loginRepository: LoginRepository): UseCase<ResultWrapper<Unit>, LoginUseCase.LoginParams> (scope){
    data class LoginParams(val email: String, val password: String)

    override fun run(params: LoginParams?) = when {
        params == null -> throw MissingParamsException()
        params.email.isEmpty() -> throw EmptyFieldException()
        params.password.isEmpty() -> throw EmptyFieldException()
        else -> loginRepository.login(params.email, params.password)
    }
}