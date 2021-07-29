package com.example.empresas.data.cache_data.dao

import androidx.room.*
import com.example.empresas.data.cache_data.model.CompanyLocal
import com.example.empresas.data.cache_data.model.HeadersLocal

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCompany(companyLocal: CompanyLocal)

    @Query("SELECT * FROM companies_table WHERE id = :idCompany")
    suspend fun getCompanyById(idCompany: Int) : CompanyLocal?

    @Query("SELECT * FROM companies_table")
    suspend fun getCompanyByFavorite() : List<CompanyLocal>?

    @Update
    suspend fun updateCompany(companyLocal: CompanyLocal)

    @Delete
    suspend fun deleteCompany(companyLocal: CompanyLocal)
}