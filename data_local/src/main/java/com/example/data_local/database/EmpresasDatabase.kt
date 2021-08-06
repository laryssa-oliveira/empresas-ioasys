package com.example.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_local.dao.CompanyDao
import com.example.data_local.dao.HeadersDao
import com.example.data_local.model.CompanyLocal
import com.example.data_local.model.CompanyTypeLocal
import com.example.data_local.model.HeadersLocal

@Database(
        entities = [HeadersLocal::class, CompanyLocal::class, CompanyTypeLocal::class],
        version = 1,
        exportSchema = false
)
abstract class EmpresasDatabase: RoomDatabase() {
    abstract fun provideHeadersDao(): HeadersDao

    abstract fun provideCompanyDao(): CompanyDao
}