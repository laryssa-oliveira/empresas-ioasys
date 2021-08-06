package com.example.data.datasource

import com.example.domain.entities.Company
import kotlinx.coroutines.flow.Flow
import okhttp3.Headers

interface LocalDataSource {

    suspend fun saveToLocalDatabase(headers: Headers)

    suspend fun getFromLocalDatabase(): Headers

    fun saveHeadersToPreferences(headers: Headers)

    fun getHeadersFromPreferences(): Headers

    fun isLogged(): Flow<Boolean>

    fun logout(): Flow<Unit>

    fun favoriteCompany(like: Boolean, company: Company): Flow<Boolean>

    suspend fun getCompanyById(id: Int): Company?

    suspend fun getCompanyByFavorite(): List<Company>
}