package com.example.empresas.presentation

import com.example.empresas.Company
import com.example.empresas.data.*
import com.example.empresas.extensions.wrapResponse



class Repository(
        private val service: CompanyService,
        private val localDataSource: LocalDataSource
) {

    suspend fun login(email: String, password: String) =
        wrapResponse{
            service.login(
                    LoginRequest(
                            email = email,
                            password = password
                    )
            ).apply {
                localDataSource.saveHeaders(headers())
            }
        }

    suspend fun getEnterprises(): ResultWrapper<List<Company>> =
            when(val result = wrapResponse {
                service.getEnterprises(
                        accessToken = localDataSource.getHeaders()["access-token"] ?: "",
                        client = localDataSource.getHeaders()["client"] ?: "",
                        uid = localDataSource.getHeaders()["uid"] ?: ""
                )
            }) {
                is ResultWrapper.Failure -> ResultWrapper.Failure(result.error!!)
                is ResultWrapper.Success -> ResultWrapper.Success(
                        result.data?.companies?.map {
                            it.toModel()
                        } ?: listOf()
                )
            }



}
