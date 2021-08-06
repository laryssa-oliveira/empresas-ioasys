package com.example.data_local.database

import androidx.room.TypeConverter
import com.example.data_local.model.CompanyTypeLocal
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