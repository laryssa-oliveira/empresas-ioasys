package com.example.empresas.data.data_remote.login

import com.example.empresas.data.LoginRepository
import com.example.empresas.data.data_local.LocalDataSource
import com.example.empresas.extensions.wrapResponse

class LoginRepositoryImpl(
        private val loginService: LoginService,
        private val localDataSource: LocalDataSource
) : LoginRepository {

    override suspend fun login(email: String, password: String) =
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

}