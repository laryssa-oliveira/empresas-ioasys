package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    fun isLogged(): Flow<Boolean>
}