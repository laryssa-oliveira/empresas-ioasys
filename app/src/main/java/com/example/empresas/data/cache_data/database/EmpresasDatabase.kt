package com.example.empresas.data.cache_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.empresas.data.cache_data.dao.CompanyDao
import com.example.empresas.data.cache_data.dao.HeadersDao
import com.example.empresas.data.cache_data.model.CompanyLocal
import com.example.empresas.data.cache_data.model.CompanyTypeLocal
import com.example.empresas.data.cache_data.model.HeadersLocal

@Database(
        entities = [HeadersLocal::class, CompanyLocal::class, CompanyTypeLocal::class],
        version = 1
)
abstract class EmpresasDatabase: RoomDatabase() {
    abstract fun provideHeadersDao(): HeadersDao

    abstract fun provideCompanyDao(): CompanyDao
}