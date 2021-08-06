package com.example.domain.usecases

import com.example.domain.core.UseCase
import com.example.domain.repository.EnterpriseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LogoutUseCase (scope: CoroutineScope, private val enterpriseRepository: EnterpriseRepository): UseCase<Unit, Unit>(scope){
    override fun run(params: Unit?): Flow<Unit> {
        return enterpriseRepository.logout()
    }
}