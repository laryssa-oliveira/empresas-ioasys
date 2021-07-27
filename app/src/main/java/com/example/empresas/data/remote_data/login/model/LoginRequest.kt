package com.example.empresas.data.remote_data.login.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val  password: String
        )
