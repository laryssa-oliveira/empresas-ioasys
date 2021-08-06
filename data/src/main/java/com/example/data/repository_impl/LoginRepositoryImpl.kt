package com.example.data.repository_impl

import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.LoginRemoteDataSource
import com.example.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override fun login(email: String, password: String) = loginRemoteDataSource.login(email, password)


}