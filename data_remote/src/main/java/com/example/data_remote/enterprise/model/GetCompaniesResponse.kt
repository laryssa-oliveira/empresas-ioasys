package com.example.data_remote.enterprise.model

import com.google.gson.annotations.SerializedName

data class GetCompaniesResponse (

        @SerializedName("enterprises")
        val companies: List<CompanyResponse>
        )
