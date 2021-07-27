package com.example.empresas.data.remote_data.enterprise.api

import com.example.empresas.data.constants.Constants.BASE_URL
import com.example.empresas.data.constants.Constants.ENTERPRISES
import com.example.empresas.data.constants.Constants.HEADER_ACCESS_TOKEN
import com.example.empresas.data.constants.Constants.HEADER_CLIENT
import com.example.empresas.data.constants.Constants.HEADER_UID
import com.example.empresas.data.remote_data.enterprise.model.GetCompaniesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.util.concurrent.TimeUnit

interface CompanyService {

    @GET(ENTERPRISES)
    suspend fun getEnterprises(
        @Header(HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(HEADER_CLIENT) client: String,
        @Header(HEADER_UID) uid: String
    ): Response<GetCompaniesResponse>

    companion object{
        fun newInstance(): CompanyService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CompanyService::class.java)

        private fun getClient(): OkHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()

    }
}