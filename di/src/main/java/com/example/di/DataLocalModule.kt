package com.example.di

import android.content.Context
import com.example.data_local.core.DatabaseConfiguration
import com.example.data.datasource.LocalDataSource
import com.example.data_local.datasource.LocalDataSourceImpl
import com.example.data.constants.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {

    fun provideSharedPreferences(context: Context) =
        context.getSharedPreferences(
            Constants.SHARED_PREF_FILE,
            Context.MODE_PRIVATE
        )

    single<LocalDataSource> { LocalDataSourceImpl(get(), get(), get()) }

    single { provideSharedPreferences(androidContext()) }

    single { DatabaseConfiguration.createDatabase(androidApplication()) }

    single {
        DatabaseConfiguration.provideHeadersDao(get())

    }

    single {
        DatabaseConfiguration.provideCompanyDao(get())
    }
}

