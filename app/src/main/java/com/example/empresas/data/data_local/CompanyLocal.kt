package com.example.empresas.data.data_local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companies_table")
data class CompanyLocal (
        @PrimaryKey var  id: Int
        )