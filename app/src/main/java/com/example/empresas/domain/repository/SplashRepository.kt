package com.example.empresas.domain.repository

import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    fun isLogged(): Flow<Boolean>
}