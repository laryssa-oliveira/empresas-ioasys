package com.example.data.datasource

import com.example.domain.entities.Company
import kotlinx.coroutines.flow.Flow

interface EnterpriseRemoteDataSource {
    fun getEnterprises(
        accessToken: String,
        client: String,
        uid: String
    ) : Flow<List<Company>>
}