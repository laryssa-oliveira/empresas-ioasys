package com.example.empresas

data class Company (
    val id: Int=0,
    val companyName: String = "",
    val description: String = "",
    val pathImage: String? = "",
    val country: String = "",
    val companyType: CompanyType? = null
)

data class CompanyType(
    val id: Int=0,
    val companyTypeName: String = ""

)
