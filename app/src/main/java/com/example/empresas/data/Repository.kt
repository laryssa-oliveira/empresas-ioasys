package com.example.empresas.data

import com.example.empresas.Company
import com.example.empresas.extensions.wrapResponse
import com.example.empresas.data.Constants.HEADER_ACCESS_TOKEN
import com.example.empresas.data.Constants.HEADER_CLIENT
import com.example.empresas.data.Constants.HEADER_UID
import com.example.empresas.data.data_local.LocalDataSource
import com.example.empresas.data.data_remote.CompanyService
import com.example.empresas.data.data_remote.LoginRequest
import com.example.empresas.data.data_remote.ResultWrapper
import com.example.empresas.data.data_remote.toModel


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
                localDataSource.saveHeadersToPreferences(headers())
            }
        }

    suspend fun getEnterprises(): ResultWrapper<List<Company>> =
            when(val result = callEnterprisesEndpoint()) {
                is ResultWrapper.Failure -> ResultWrapper.Failure(result.error!!)
                is ResultWrapper.Success -> ResultWrapper.Success(
                        result.data?.companies?.map {
                            it.toModel()
                        } ?: listOf()
                )
            }

    private suspend fun callEnterprisesEndpoint() = wrapResponse {
        service.getEnterprises(
                accessToken = localDataSource.getHeadersFromPreferences()[HEADER_ACCESS_TOKEN] ?: "",
                client = localDataSource.getHeadersFromPreferences()[HEADER_CLIENT] ?: "",
                uid = localDataSource.getHeadersFromPreferences()[HEADER_UID] ?: ""
        )
    }

}
