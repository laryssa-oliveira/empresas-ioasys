package com.example.empresas.data.cache_data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.empresas.data.cache_data.dao.CompanyDao
import okhttp3.Headers
import com.example.empresas.data.constants.Constants.KEY_ACCESS_TOKEN
import com.example.empresas.data.constants.Constants.KEY_CLIENT
import com.example.empresas.data.constants.Constants.KEY_UID
import com.example.empresas.data.constants.Constants.HEADER_ACCESS_TOKEN
import com.example.empresas.data.constants.Constants.HEADER_CLIENT
import com.example.empresas.data.constants.Constants.HEADER_UID
import com.example.empresas.data.cache_data.dao.HeadersDao
import com.example.empresas.data.cache_data.mapper.LocalModelMappers.fromLocalModel
import com.example.empresas.data.cache_data.mapper.LocalModelMappers.toLocalModel
import com.example.empresas.data.cache_data.mapper.LocalModelMappers.toModel
import com.example.empresas.domain.entities.Company
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(
        private val sharedPreferences: SharedPreferences,
        private val headersDao: HeadersDao,
        private val companyDao: CompanyDao
) : LocalDataSource {

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

    override fun isLogged() = flow {

            val token = sharedPreferences.getString(KEY_ACCESS_TOKEN, "") ?: ""
            val client = sharedPreferences.getString(KEY_CLIENT, "") ?: ""
            val uid = sharedPreferences.getString(KEY_UID, "") ?: ""

        emit(
            token.isNotEmpty() && client.isNotEmpty() && uid.isNotEmpty()
        )
    }

    override fun logout() = flow {
        sharedPreferences.edit {
            remove(KEY_ACCESS_TOKEN)
            remove(KEY_CLIENT)
            remove(KEY_UID)
        }
        emit(Unit)
    }

    override fun favoriteCompany(like: Boolean, company: Company) = flow {
        if(like){
            companyDao.deleteCompany(company.toLocalModel())
        }
        else{
            companyDao.createCompany(company.apply { favorite = true }.toLocalModel())
        }
        emit(!like)
    }

    override suspend fun getCompanyById(id: Int) = companyDao.getCompanyById(id)?.toModel()
}