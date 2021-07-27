package com.example.empresas.data.remote_data.core

import com.example.empresas.domain.entities.Company
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

sealed class ResultWrapper<out Type>(
        val data: Type? = null,
        val error: Throwable? = null
) {

    class Success<Type>(data: Type) : ResultWrapper<Type>(data), Flow<ResultWrapper<List<Company>>> {
        @InternalCoroutinesApi
        override suspend fun collect(collector: FlowCollector<ResultWrapper<List<Company>>>) {

        }
    }

    class Failure(error: Throwable) : ResultWrapper<Nothing>(error = error)
}