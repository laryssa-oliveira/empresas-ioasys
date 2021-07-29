package com.example.empresas.data.cache_data.mapper

import com.example.empresas.data.cache_data.model.CompanyLocal
import com.example.empresas.data.cache_data.model.CompanyTypeLocal
import com.example.empresas.data.cache_data.model.HeadersLocal
import com.example.empresas.data.constants.Constants.HEADER_ACCESS_TOKEN
import com.example.empresas.data.constants.Constants.HEADER_CLIENT
import com.example.empresas.data.constants.Constants.HEADER_UID
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.entities.CompanyType
import okhttp3.Headers
import java.util.Collections.list

object LocalModelMappers {

    fun Headers.toLocalModel() =
            HeadersLocal(
                    token = this[HEADER_ACCESS_TOKEN] ?: "",
                    client = this[HEADER_CLIENT] ?: "",
                    uid = this[HEADER_UID] ?: ""

            )
    fun HeadersLocal.fromLocalModel() =
            Headers.Builder()
                    .add(HEADER_ACCESS_TOKEN, token)
                    .add(HEADER_CLIENT, client)
                    .add(HEADER_UID, uid)
                    .build()

        fun Company.toLocalModel() =
                CompanyLocal(
                        id = id,
                        companyName = companyName,
                        description = description,
                        pathImage = pathImage,
                        country = country,
                        companyTypeLocal = companyType?.toLocalModel(),
                        favorite = favorite
                )

        fun CompanyType.toLocalModel() =
                CompanyTypeLocal(
                        id = id,
                        companyTypeName = companyTypeName
                )

        fun CompanyLocal.toModel() =
                Company(
                        id = id,
                        companyName = companyName,
                        description = description,
                        pathImage = pathImage,
                        country = country,
                        companyType = companyTypeLocal?.toModel(),
                        favorite = favorite
                )

        fun CompanyTypeLocal.toModel() =
                CompanyType(
                        id = id,
                        companyTypeName = companyTypeName
                )

        fun List<CompanyLocal>.toModel() = this.map { it.toModel() }


}