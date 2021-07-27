package com.example.empresas.presentation

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empresas.R
import com.example.empresas.data.cache_data.database.EmpresasDatabase
import com.example.empresas.domain.repository.EnterpriseRepository
import com.example.empresas.data.remote_data.core.ResultWrapper
import com.example.empresas.domain.core.useCase
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.usecases.*
import com.example.empresas.extensions.viewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class MainViewModel : ViewModel(), KoinComponent {

    private val mainUseCase: MainUseCase by useCase()

    private val filterCompanyUseCase: FilterCompanyUseCase by useCase()
    private val favoriteCompanyUseCase: FavoriteCompanyUseCase by useCase()
    private val _companyListLiveData by viewState<List<Company>>()
    private val _filterCompany by viewState<List<Company>>()

    private val _favoriteCompany by viewState<Unit>()
    val companyListLiveData: LiveData<ViewState<List<Company>>> = _companyListLiveData
    val filterCompany: LiveData<ViewState<List<Company>>> = _filterCompany

    val favoriteCompany: LiveData<ViewState<Unit>> = _favoriteCompany
    private var listCompany = mutableListOf<Company>()

    fun getCompanies() {
        Log.d("getCompanies", "view model")
        _companyListLiveData.value = ViewState.loading(true)
        mainUseCase(
            onError = {
                _companyListLiveData.value = ViewState.error(it)
                _companyListLiveData.value = ViewState.loading(false)
            },
            onSuccess = {
                listCompany.clear()
                listCompany.addAll(it)
                _companyListLiveData.value = ViewState.loading(false)
                _companyListLiveData.value = ViewState.success(it)
            }
        )
    }



    fun filter(term: String) {
        filterCompanyUseCase(params = FilterCompanyUseCase.FilterCompanyParams(listCompany, term),
            onSuccess = {
                _filterCompany.value = ViewState.success(it)
            },
            onError = {
                _filterCompany.value = ViewState.error(it)
            })
    }

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
