package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(email: String, password: String) : Flow<Unit>
}