package com.example.empresas.data.repository_impl

import com.example.empresas.data.cache_data.datasource.LocalDataSource
import com.example.empresas.data.remote_data.login.model.LoginRequest
import com.example.empresas.data.remote_data.login.api.LoginService
import com.example.empresas.domain.repository.LoginRepository
import com.example.empresas.extensions.wrapResponse
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(
    private val loginService: LoginService,
    private val localDataSource: LocalDataSource
) : LoginRepository {

    override fun login(email: String, password: String) = flow {
        emit(
            wrapResponse{
                loginService.login(
                    LoginRequest(
                        email = email,
                        password = password
                    )
                ).apply {
                    localDataSource.saveHeadersToPreferences(headers())
                }
            }
        )
    }


}