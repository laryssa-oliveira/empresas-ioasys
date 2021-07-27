package com.example.empresas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.empresas.domain.core.useCase
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.usecases.FavoriteCompanyUseCase
import com.example.empresas.domain.usecases.MainUseCase
import com.example.empresas.extensions.viewState
import org.koin.core.KoinComponent

class DetailsViewModel : ViewModel(), KoinComponent {
    private val favoriteCompanyUseCase: FavoriteCompanyUseCase by useCase()
    private val _favoriteCompany by viewState<Unit>()
    val favoriteCompany: LiveData<ViewState<Unit>> = _favoriteCompany

    fun favorite(like: Boolean, company: Company){
        favoriteCompanyUseCase(params = FavoriteCompanyUseCase.FavoriteCompanyParams(like, company),
            onSuccess = {
                _favoriteCompany.value = ViewState.success(Unit)
            },
            onError = {
                _favoriteCompany.value = ViewState.error(it)
            })
    }


}