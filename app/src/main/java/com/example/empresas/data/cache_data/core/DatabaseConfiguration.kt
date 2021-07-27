package com.example.empresas.data.cache_data.core

import android.content.Context
import androidx.room.Room
import com.example.empresas.data.cache_data.database.EmpresasDatabase

object DatabaseConfiguration {

    private var databaseInstance : EmpresasDatabase? = null

    fun getDatabaseInstance(context: Context) =
            if (databaseInstance == null) {
                createDatabase(context)
            } else
                databaseInstance!!

    private fun createDatabase(context: Context): EmpresasDatabase {
        databaseInstance = Room.databaseBuilder(
                context,
                EmpresasDatabase::class.java,
                "empresas_database"
        ).build()
        return databaseInstance!!
    }

}