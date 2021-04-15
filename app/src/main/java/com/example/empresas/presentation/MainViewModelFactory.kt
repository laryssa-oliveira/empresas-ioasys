package com.example.empresas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.empresas.data.CompanyService
import com.example.empresas.data.LocalDataSource

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  modelClass.getConstructor(CompanyService::class.java)
                .newInstance(
                        CompanyService.newInstance(),
                        LocalDataSource()
                )
    }
}