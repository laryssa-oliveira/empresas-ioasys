package com.example.feature_main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.base_feature.ViewState
import com.example.base_feature.useCase
import com.example.base_feature.viewState
import com.example.domain.entities.Company
import com.example.domain.usecases.FavoriteCompanyUseCase
import com.example.domain.usecases.FilterCompanyUseCase
import com.example.domain.usecases.ListFavoriteUseCase
import org.koin.core.KoinComponent

class FavoriteCompaniesViewModel : ViewModel(), KoinComponent {

    private val listFavoriteUseCase: ListFavoriteUseCase by useCase()
    private val favoriteCompanyUseCase: FavoriteCompanyUseCase by useCase()
    private val _companyListLiveData by viewState<List<Company>>()
    val companyListLiveData: LiveData<ViewState<List<Company>>> = _companyListLiveData
    private var listCompanyFavorite = mutableListOf<Company>()
    private val _favoriteCompany by viewState<Unit>()
    val favoriteCompany: LiveData<ViewState<Unit>> = _favoriteCompany
    private val _filterCompany by viewState<List<Company>>()
    val filterCompany: LiveData<ViewState<List<Company>>> = _filterCompany
    private val filterCompanyUseCase: FilterCompanyUseCase by useCase()


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

    fun filter(term: String) {
        filterCompanyUseCase(params = FilterCompanyUseCase.FilterCompanyParams(listCompanyFavorite, term),
            onSuccess = {
                _filterCompany.value = ViewState.success(it)
            },
            onError = {
                _filterCompany.value = ViewState.error(it)
            })
    }
}