package com.example.empresas.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.empresas.data.Repository
import com.example.empresas.data.data_remote.CompanyService
import com.example.empresas.data.data_local.LocalDataSource
import com.example.empresas.injection.Injection.provideRepository

class MainViewModelFactory(
        private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  modelClass.getConstructor(Repository::class.java)
                .newInstance(provideRepository(context))
    }

    companion object{
        fun create(from : ViewModelStoreOwner, context: Context) =
                ViewModelProvider(
                        from,
                        MainViewModelFactory(context)
                ).get(MainViewModel::class.java)
    }
}