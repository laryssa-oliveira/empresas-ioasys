package com.example.feature_main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.base_feature.ViewState
import com.example.base_feature.useCase
import com.example.base_feature.viewState
import com.example.domain.entities.Company
import com.example.domain.usecases.FavoriteCompanyUseCase
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