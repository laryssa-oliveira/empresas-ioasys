package com.example.empresas.data.data_local

import android.content.SharedPreferences
import okhttp3.Headers
import com.example.empresas.data.Constants.KEY_ACCESS_TOKEN
import com.example.empresas.data.Constants.KEY_CLIENT
import com.example.empresas.data.Constants.KEY_UID
import com.example.empresas.data.Constants.HEADER_ACCESS_TOKEN
import com.example.empresas.data.Constants.HEADER_CLIENT
import com.example.empresas.data.Constants.HEADER_UID
import com.example.empresas.data.data_local.LocalModelMappers.fromLocalModel
import com.example.empresas.data.data_local.LocalModelMappers.toLocalModel

class LocalDataSourceImpl(
        private val sharedPreferences: SharedPreferences,
        private val headersDao: HeadersDao
) : LocalDataSource{

    override suspend fun saveToLocalDatabase(headers: Headers) {
        headersDao.saveHeaders(headers.toLocalModel())
    }

    override suspend fun getFromLocalDatabase() =
        headersDao.getHeaders().fromLocalModel()

    override fun saveHeadersToPreferences(headers: Headers){
        sharedPreferences.apply {
            val editor = edit()
            editor.putString(KEY_ACCESS_TOKEN, headers[HEADER_ACCESS_TOKEN])
            editor.putString(KEY_CLIENT, headers[HEADER_CLIENT])
            editor.putString(KEY_UID, headers[HEADER_UID])
            editor.apply()
        }
    }

    override fun getHeadersFromPreferences() = sharedPreferences.run {
            val token = getString(KEY_ACCESS_TOKEN, "") ?: ""
            val client = getString(KEY_CLIENT, "") ?: ""
            val uid = getString(KEY_UID, "") ?: ""
            Headers.Builder()
                    .add(HEADER_ACCESS_TOKEN, token)
                    .add(HEADER_CLIENT, client)
                    .add(HEADER_UID, uid)
                    .build()
    }
}