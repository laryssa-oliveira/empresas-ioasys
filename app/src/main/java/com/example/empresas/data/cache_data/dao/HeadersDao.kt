package com.example.empresas.data.cache_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.empresas.data.cache_data.model.HeadersLocal

@Dao
interface HeadersDao {

    @Insert
    suspend fun saveHeaders(headers: HeadersLocal)

    @Query("SELECT * FROM headers_table;")
    suspend fun getHeaders() : HeadersLocal
}