package com.example.empresas.domain.repository

import com.example.empresas.data.remote_data.core.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(email: String, password: String) : Flow<ResultWrapper<Unit>>
}