package com.example.empresas.data.repository_impl

import com.example.empresas.extensions.wrapResponse
import com.example.empresas.data.constants.Constants.HEADER_ACCESS_TOKEN
import com.example.empresas.data.constants.Constants.HEADER_CLIENT
import com.example.empresas.data.constants.Constants.HEADER_UID
import com.example.empresas.data.cache_data.datasource.LocalDataSource
import com.example.empresas.data.remote_data.core.ResultWrapper
import com.example.empresas.data.remote_data.enterprise.api.CompanyService
import com.example.empresas.data.remote_data.mapper.fromListResponse
import com.example.empresas.data.remote_data.mapper.toModel
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.repository.EnterpriseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


class EnterpriseRepositoryImpl(
    private val service: CompanyService,
    private val localDataSource: LocalDataSource
) : EnterpriseRepository {

    override fun getEnterprises(): Flow<List<Company>> = flow {
        emit(
            syncWithLocalInfo(
                wrapResponse {
                    service.getEnterprises(
                        accessToken = localDataSource.getHeadersFromPreferences()[HEADER_ACCESS_TOKEN]
                            ?: "",
                        client = localDataSource.getHeadersFromPreferences()[HEADER_CLIENT] ?: "",
                        uid = localDataSource.getHeadersFromPreferences()[HEADER_UID] ?: ""
                    )
                }.data?.fromListResponse()!!
            )
        )
    }

    override fun logout(): Flow<Unit> {
        return localDataSource.logout()
    }

    override fun favoriteCompany(like: Boolean, company: Company): Flow<Boolean> {
        return localDataSource.favoriteCompany(like, company)
    }

    private suspend fun syncWithLocalInfo(companies: List<Company>): List<Company> {
        val companySync = mutableListOf<Company>()
        companies.map { company ->
            val companyLocal = localDataSource.getCompanyById(company.id)
            if (companyLocal == null) {
                companySync.add(company)
            } else {
                companySync.add(companyLocal)
            }
        }
        return companySync
    }

}

