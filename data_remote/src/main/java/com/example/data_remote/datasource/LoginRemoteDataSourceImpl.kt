package com.example.data_remote.datasource

import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.LoginRemoteDataSource
import com.example.data_remote.core.wrapResponse
import com.example.data_remote.login.api.LoginService
import com.example.data_remote.login.model.LoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRemoteDataSourceImpl(
    private val loginService: LoginService,
    private val localDataSource: LocalDataSource
): LoginRemoteDataSource {
    override fun login(email: String, password: String) = flow {
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
        emit(Unit)
    }
}