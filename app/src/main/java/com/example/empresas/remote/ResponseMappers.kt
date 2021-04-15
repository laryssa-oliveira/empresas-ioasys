package com.example.empresas.remote

import com.example.empresas.Company
import com.example.empresas.CompanyType

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