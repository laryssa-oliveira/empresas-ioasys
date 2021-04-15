package com.example.empresas.data

import com.google.gson.annotations.SerializedName

data class GetCompaniesResponse (

        @SerializedName("enterprises")
        val companies: List<CompanyResponse>
        )
