package com.example.empresas.data

import com.example.empresas.data.data_remote.ResultWrapper
import okhttp3.Headers

interface LoginRepository {
    suspend fun login(email: String, password: String) : ResultWrapper<Unit>
}