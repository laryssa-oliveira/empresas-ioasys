package com.example.empresas.data.cache_data.database

import androidx.room.TypeConverter
import com.example.empresas.data.cache_data.model.CompanyTypeLocal
import com.example.empresas.domain.entities.CompanyType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverter {
    @TypeConverter
    fun toCompanyTypeLocal(string: String) : CompanyTypeLocal {
        val type = object : TypeToken<CompanyTypeLocal>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromCompanyTypeLocal(string: CompanyTypeLocal) : String {
        return Gson().toJson(string)
    }
}