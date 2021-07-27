package com.example.empresas.di

import android.content.Context
import com.example.empresas.data.constants.Constants
import com.example.empresas.data.cache_data.core.DatabaseConfiguration
import com.example.empresas.data.cache_data.datasource.LocalDataSource
import com.example.empresas.data.cache_data.datasource.LocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {

    fun provideSharedPreferences(context: Context) =
            context.getSharedPreferences(
                    Constants.SHARED_PREF_FILE,
                    Context.MODE_PRIVATE
            )

    single<LocalDataSource> { LocalDataSourceImpl(get(),get(), get()) }

    single { provideSharedPreferences(androidContext()) }

    single {
        DatabaseConfiguration
                .getDatabaseInstance(androidContext())
                .provideHeadersDao()

    }

    single {
        DatabaseConfiguration
            .getDatabaseInstance(androidContext())
            .provideCompanyDao()

    }
}

