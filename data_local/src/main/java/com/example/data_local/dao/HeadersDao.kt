package com.example.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data_local.model.HeadersLocal

@Dao
interface HeadersDao {

    @Insert
    suspend fun saveHeaders(headers: HeadersLocal)

    @Query("SELECT * FROM headers_table")
    suspend fun getHeaders() : HeadersLocal
}