package com.example.empresas.extensions

import com.example.empresas.data.ResultWrapper
import retrofit2.Response

suspend fun <T> wrapResponse(
        call: suspend () -> Response<T>

): ResultWrapper<T> {
    val result = call()
   return try {
        ResultWrapper.Success(result.body()!!)
    } catch (error: Throwable){
        ResultWrapper.Failure(error)
    }
}

