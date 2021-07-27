package com.example.empresas.domain.usecases

import com.example.empresas.data.remote_data.core.ResultWrapper
import com.example.empresas.domain.core.UseCase
import com.example.empresas.domain.repository.EnterpriseRepository
import com.example.empresas.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LogoutUseCase (scope: CoroutineScope, private val enterpriseRepository: EnterpriseRepository): UseCase<Unit, Unit>(scope){
    override fun run(params: Unit?): Flow<Unit> {
        return enterpriseRepository.logout()
    }
}