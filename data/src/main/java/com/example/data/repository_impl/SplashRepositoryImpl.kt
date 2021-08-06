package com.example.data.repository_impl

import com.example.data.datasource.LocalDataSource
import com.example.domain.repository.SplashRepository
import kotlinx.coroutines.flow.Flow

class SplashRepositoryImpl(
    private val localDataSource: LocalDataSource
): SplashRepository {
    override fun isLogged(): Flow<Boolean> {
        return localDataSource.isLogged()
    }
}