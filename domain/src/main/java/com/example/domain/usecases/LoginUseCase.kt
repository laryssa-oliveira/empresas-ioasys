package com.example.domain.usecases

import com.example.domain.core.UseCase
import com.example.domain.exceptions.EmptyFieldException
import com.example.domain.exceptions.MissingParamsException
import com.example.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope

class LoginUseCase(scope: CoroutineScope, private val loginRepository: LoginRepository): UseCase<Unit, LoginUseCase.LoginParams>(scope){
    data class LoginParams(val email: String, val password: String)

    override fun run(params: LoginParams?) = when {
        params == null -> throw MissingParamsException()
        params.email.isEmpty() -> throw EmptyFieldException()
        params.password.isEmpty() -> throw EmptyFieldException()
        else -> loginRepository.login(params.email, params.password)
    }
}