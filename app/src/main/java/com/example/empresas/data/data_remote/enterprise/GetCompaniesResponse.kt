package com.example.empresas.data.data_remote.enterprise

import com.example.empresas.data.data_remote.enterprise.CompanyResponse
import com.google.gson.annotations.SerializedName

data class GetCompaniesResponse (

        @SerializedName("enterprises")
        val companies: List<CompanyResponse>
        )
