package com.example.empresas.data.data_remote

import com.example.empresas.Company
import com.example.empresas.CompanyType
import com.example.empresas.data.data_remote.enterprise.CompanyResponse
import com.example.empresas.data.data_remote.enterprise.CompanyTypeResponse

fun CompanyResponse.toModel(): Company {
    return Company(
            id = id,
            companyName = enterpriseName,
            pathImage = "https://thispersondoesnotexist.com/image",
            description = description,
            country = country ?: "",
            companyType = enterpriseType.toModel()
    )
}

fun CompanyTypeResponse.toModel(): CompanyType {
    return CompanyType(
            id = id,
            companyTypeName = enterpriseTypeName
    )
}