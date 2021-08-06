package com.example.data_remote.mapper

import com.example.data_remote.enterprise.model.CompanyResponse
import com.example.data_remote.enterprise.model.CompanyTypeResponse
import com.example.data_remote.enterprise.model.GetCompaniesResponse
import com.example.domain.entities.Company
import com.example.domain.entities.CompanyType

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