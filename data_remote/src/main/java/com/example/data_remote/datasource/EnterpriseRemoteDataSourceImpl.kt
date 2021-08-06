package com.example.data_remote.datasource

import com.example.data.constants.Constants
import com.example.data.datasource.EnterpriseRemoteDataSource
import com.example.data_remote.core.wrapResponse
import com.example.data_remote.enterprise.api.CompanyService
import com.example.data_remote.mapper.fromListResponse
import com.example.domain.entities.Company
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EnterpriseRemoteDataSourceImpl(private val service: CompanyService): EnterpriseRemoteDataSource {
    override fun getEnterprises(
        accessToken: String,
        client: String,
        uid: String
    ): Flow<List<Company>> = flow {
        emit(
                wrapResponse {
                    service.getEnterprises(
                        accessToken = accessToken,
                        client = client,
                        uid = uid
                    )
                }.data?.fromListResponse()!!
            )
    }
}