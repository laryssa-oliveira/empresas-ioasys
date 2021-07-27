package com.example.empresas.domain.repository

import com.example.empresas.domain.entities.Company
import com.example.empresas.data.remote_data.core.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface EnterpriseRepository {
    fun getEnterprises() : Flow<List<Company>>
    fun logout(): Flow<Unit>
    fun favoriteCompany(like: Boolean, company: Company): Flow<Boolean>

}