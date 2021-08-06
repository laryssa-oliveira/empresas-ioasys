package com.example.data.repository_impl

import com.example.data.constants.Constants.HEADER_ACCESS_TOKEN
import com.example.data.constants.Constants.HEADER_CLIENT
import com.example.data.constants.Constants.HEADER_UID
import com.example.data.datasource.EnterpriseRemoteDataSource
import com.example.data.datasource.LocalDataSource
import com.example.domain.entities.Company
import com.example.domain.repository.EnterpriseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single


class EnterpriseRepositoryImpl(
    private val enterpriseRemoteDataSource: EnterpriseRemoteDataSource,
    private val localDataSource: LocalDataSource
) : EnterpriseRepository {

    override fun getEnterprises() = flow {
        val accessToken = localDataSource.getHeadersFromPreferences()[HEADER_ACCESS_TOKEN] ?: ""
        val client = localDataSource.getHeadersFromPreferences()[HEADER_CLIENT] ?: ""
        val uid = localDataSource.getHeadersFromPreferences()[HEADER_UID] ?: ""

        emit(
            syncWithLocalInfo(enterpriseRemoteDataSource.getEnterprises(accessToken, client, uid).single())
        )
    }

    override fun getFavoriteEnterprises(): Flow<List<Company>> = flow {
        emit(
            localDataSource.getCompanyByFavorite()
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

