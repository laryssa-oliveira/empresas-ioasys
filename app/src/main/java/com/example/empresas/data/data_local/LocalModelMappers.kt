package com.example.empresas.data.data_local
import com.example.empresas.data.Constants.HEADER_ACCESS_TOKEN
import com.example.empresas.data.Constants.HEADER_CLIENT
import com.example.empresas.data.Constants.HEADER_UID

import okhttp3.Headers

object LocalModelMappers {

    fun Headers.toLocalModel() =
            HeadersLocal(
                    token = this[HEADER_ACCESS_TOKEN] ?: "",
                    client = this[HEADER_CLIENT] ?: "",
                    uid = this[HEADER_UID] ?: ""

            )
    fun HeadersLocal.fromLocalModel() =
            Headers.Builder()
                    .add(HEADER_ACCESS_TOKEN, token)
                    .add(HEADER_CLIENT, client)
                    .add(HEADER_UID, uid)
                    .build()

}