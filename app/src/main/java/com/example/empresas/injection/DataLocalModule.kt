package com.example.empresas.injection

import android.content.Context
import com.example.empresas.data.Constants
import com.example.empresas.data.data_local.DatabaseConfiguration
import com.example.empresas.data.data_local.HeadersDao
import com.example.empresas.data.data_local.LocalDataSource
import com.example.empresas.data.data_local.LocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {

    fun provideSharedPreferences(context: Context) =
            context.getSharedPreferences(
                    Constants.SHARED_PREF_FILE,
                    Context.MODE_PRIVATE
            )

    single<LocalDataSource> { LocalDataSourceImpl(get(),get()) }

    single { provideSharedPreferences(androidContext()) }

    single {
        DatabaseConfiguration
                .getDatabaseInstance(androidContext())
                .provideHeadersDao()

    }
}

