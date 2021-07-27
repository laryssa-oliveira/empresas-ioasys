package com.example.empresas.data.repository_impl

import com.example.empresas.data.cache_data.datasource.LocalDataSource
import com.example.empresas.domain.repository.SplashRepository
import kotlinx.coroutines.flow.Flow

class SplashRepositoryImpl(
    private val localDataSource: LocalDataSource
): SplashRepository {
    override fun isLogged(): Flow<Boolean> {
        return localDataSource.isLogged()
    }
}