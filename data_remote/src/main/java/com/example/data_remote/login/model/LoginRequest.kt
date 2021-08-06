package com.example.data_remote.login.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val  password: String
        )
