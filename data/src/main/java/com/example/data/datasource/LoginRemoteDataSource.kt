package com.example.data.datasource

import kotlinx.coroutines.flow.Flow

interface LoginRemoteDataSource {
    fun login(email: String, password: String) : Flow<Unit>
}