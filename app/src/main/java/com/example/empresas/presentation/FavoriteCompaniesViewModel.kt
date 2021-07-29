package com.example.empresas.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.empresas.domain.core.useCase
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.usecases.FavoriteCompanyUseCase
import com.example.empresas.domain.usecases.ListFavoriteUseCase
import com.example.empresas.domain.usecases.MainUseCase
import com.example.empresas.extensions.viewState
import org.koin.core.KoinComponent

class FavoriteCompaniesViewModel : ViewModel(), KoinComponent {

    private val listFavoriteUseCase: ListFavoriteUseCase by useCase()
    private val favoriteCompanyUseCase: FavoriteCompanyUseCase by useCase()
    private val _companyListLiveData by viewState<List<Company>>()
    val companyListLiveData: LiveData<ViewState<List<Company>>> = _companyListLiveData
    private var listCompanyFavorite = mutableListOf<Company>()
    private lateinit var company: Company
    private val _favoriteCompany by viewState<Unit>()
    val favoriteCompany: LiveData<ViewState<Unit>> = _favoriteCompany


    fun getFavoriteCompanies() {
        Log.d("getFavoriteCompanies", "view model")
        _companyListLiveData.value = ViewState.loading(true)
        listFavoriteUseCase(
            onError = {
                _companyListLiveData.value = ViewState.error(it)
                _companyListLiveData.value = ViewState.loading(false)
            },
            onSuccess = {
                listCompanyFavorite.clear()
                listCompanyFavorite.addAll(it)
                _companyListLiveData.value = ViewState.loading(false)
                _companyListLiveData.value = ViewState.success(it)
            }
        )
    }

    fun favoriteCompanies(like: Boolean, company: Company){
        favoriteCompanyUseCase(params = FavoriteCompanyUseCase.FavoriteCompanyParams(like, company),
            onSuccess = {
                _favoriteCompany.value = ViewState.success(Unit)
            },
            onError = {
                _favoriteCompany.value = ViewState.error(it)
            })
    }
}