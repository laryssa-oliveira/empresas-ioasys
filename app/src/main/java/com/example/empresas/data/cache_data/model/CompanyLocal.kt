package com.example.empresas.data.cache_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.empresas.data.cache_data.database.DatabaseConverter
import com.example.empresas.domain.entities.CompanyType

@Entity(tableName = "companies_table")
@TypeConverters(DatabaseConverter::class)
data class CompanyLocal (
        @PrimaryKey var  id: Int,
        var favorite: Boolean = false,
        val companyName: String = "",
        val description: String = "",
        val pathImage: String? = "",
        val country: String = "",
        val companyTypeLocal: CompanyTypeLocal?
        )

@Entity(tableName = "companies_type_table")
class CompanyTypeLocal (
        @PrimaryKey val id: Int,
        val companyTypeName: String = ""
        )
