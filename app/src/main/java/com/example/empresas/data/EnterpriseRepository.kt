package com.example.empresas.data

import com.example.empresas.Company
import com.example.empresas.data.data_remote.ResultWrapper

interface EnterpriseRepository {
    suspend fun getEnterprises() : ResultWrapper<List<Company>>

}