package com.example.empresas.data.data_local

import androidx.room.*

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCompany(companyLocal: CompanyLocal)

    @Query("SELECT * FROM companies_table WHERE id = :idCompany")
    suspend fun getCompanyById(idCompany: Int) : List<CompanyLocal>

    @Update
    suspend fun updateCompany(companyLocal: CompanyLocal)

    @Delete
    suspend fun deleteCompany(headersLocal: HeadersLocal)
}