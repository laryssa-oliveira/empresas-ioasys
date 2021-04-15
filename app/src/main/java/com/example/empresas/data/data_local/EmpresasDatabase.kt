package com.example.empresas.data.data_local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [HeadersLocal::class, CompanyLocal::class],
        version = 1
)

abstract class EmpresasDatabase : RoomDatabase() {
    abstract fun provideHeadersDao(): HeadersDao

    abstract fun provideCompanyDao(): CompanyDao
}