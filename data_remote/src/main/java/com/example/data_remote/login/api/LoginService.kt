package com.example.data_remote.login.api

import com.example.data.constants.Constants.BASE_URL
import com.example.data.constants.Constants.SIGNIN_URL
import com.example.data_remote.login.model.LoginRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface LoginService {

    @POST(SIGNIN_URL)
    suspend fun login(
            @Body loginRequest: LoginRequest
    ): Response<Unit>


    companion object{
        fun newInstance(): LoginService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(LoginService::class.java)

        private fun getClient(): OkHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()

    }
}