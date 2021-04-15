package com.example.empresas.data.data_remote

import com.example.empresas.data.data_remote.CompanyResponse
import com.google.gson.annotations.SerializedName

data class GetCompaniesResponse (

        @SerializedName("enterprises")
        val companies: List<CompanyResponse>
        )
