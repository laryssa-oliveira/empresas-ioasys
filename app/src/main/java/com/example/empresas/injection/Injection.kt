package com.example.empresas.injection

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.example.empresas.data.Constants
import com.example.empresas.data.Repository
import com.example.empresas.data.data_local.DatabaseConfiguration
import com.example.empresas.data.data_local.LocalDataSource
import com.example.empresas.data.data_remote.CompanyService
import com.example.empresas.presentation.LoginViewModelFactory
import com.example.empresas.presentation.MainViewModel
import com.example.empresas.presentation.MainViewModelFactory

object Injection {

    fun provideMainViewModel(from: ViewModelStoreOwner, context: Context) =
            MainViewModelFactory.create(from, context)

    fun provideLoginViewModel(from: ViewModelStoreOwner, context: Context) =
            LoginViewModelFactory.create(from, context)

    fun provideRepository(context: Context) =
            Repository(
                    provideCompanyService(),
                    provideLocalDataSource(context)
            )

    private fun provideCompanyService() =
            CompanyService.newInstance()

    private fun provideLocalDataSource(context: Context) =
            LocalDataSource(
                    provideSharedPreferences(context),
                    provideHeadersDao(context),
                    provideCompanyDao(context)
            )

    private fun provideCompanyDao(context: Context) =
            DatabaseConfiguration.getDatabaseInstance(context).provideCompanyDao()

    private fun provideHeadersDao(context: Context) =
            DatabaseConfiguration.getDatabaseInstance(context).provideHeadersDao()

    private fun provideSharedPreferences(context: Context) =
            context.getSharedPreferences(
                    Constants.SHARED_PREF_FILE,
                    Context.MODE_PRIVATE
            )
}