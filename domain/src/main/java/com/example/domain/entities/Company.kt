package com.example.domain.entities

import java.io.Serializable

data class Company (
    val id: Int=0,
    val companyName: String = "",
    val description: String = "",
    val pathImage: String? = "",
    val country: String = "",
    val companyType: CompanyType? = null,
    var favorite: Boolean = false
): Serializable

data class CompanyType(
    val id: Int=0,
    val companyTypeName: String = ""

): Serializable
