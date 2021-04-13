package com.example.empresas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas.Company
import com.example.empresas.remote.CompanyService
import com.example.empresas.remote.GetCompaniesResponse
import com.example.empresas.remote.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
        private val service: CompanyService
) : ViewModel() {
    private val _companyListLiveData = MutableLiveData<List<Company>>()
    val companyListLiveData: LiveData<List<Company>> = _companyListLiveData

    fun getCompanies(accessToken: String, clientId: String, uid: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = service.getEnterprises(
                    accessToken = accessToken,
                    client = clientId,
                    uid = uid
            )

            handleResponse(response)
        }
    }


    private fun handleResponse(response: Response<GetCompaniesResponse>) {
        if (response.isSuccessful) {
            _companyListLiveData.value = response.body()?.companies?.map{ it.toModel() }
        }
    }

}
