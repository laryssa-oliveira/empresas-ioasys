package com.example.empresas.data.remote_data.enterprise.model

import com.google.gson.annotations.SerializedName

data class GetCompaniesResponse (

        @SerializedName("enterprises")
        val companies: List<CompanyResponse>
        )
