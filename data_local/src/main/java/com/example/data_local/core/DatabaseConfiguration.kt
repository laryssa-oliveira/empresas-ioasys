package com.example.data_local.core

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data_local.database.EmpresasDatabase

object DatabaseConfiguration {

    fun createDatabase(application: Application) = Room.databaseBuilder(
                application,
                EmpresasDatabase::class.java,
                "empresas_database"
        ).build()

    fun provideHeadersDao(database: EmpresasDatabase) = database.provideHeadersDao()
    fun provideCompanyDao(database: EmpresasDatabase) = database.provideCompanyDao()

}