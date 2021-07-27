package com.example.empresas.data.remote_data.mapper

import com.example.empresas.data.remote_data.enterprise.model.CompanyResponse
import com.example.empresas.data.remote_data.enterprise.model.CompanyTypeResponse
import com.example.empresas.data.remote_data.enterprise.model.GetCompaniesResponse
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.entities.CompanyType

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

fun GetCompaniesResponse.fromListResponse(): List<Company>{
    return this.companies.map { it.toModel() }
}