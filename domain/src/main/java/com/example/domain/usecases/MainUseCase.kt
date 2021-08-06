package com.example.domain.usecases

import com.example.domain.core.UseCase
import com.example.domain.entities.Company
import com.example.domain.repository.EnterpriseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class MainUseCase(scope: CoroutineScope, private val enterpriseRepository: EnterpriseRepository): UseCase<List<Company>, Unit>(scope){

    override fun run(params: Unit?): Flow<List<Company>> {
        return enterpriseRepository.getEnterprises()
    }

}