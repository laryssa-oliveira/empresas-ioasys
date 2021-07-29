package com.example.empresas.domain.usecases

import com.example.empresas.domain.core.UseCase
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.repository.EnterpriseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class ListFavoriteUseCase (scope: CoroutineScope, private val enterpriseRepository: EnterpriseRepository): UseCase<List<Company>, Unit>(scope){

    override fun run(params: Unit?): Flow<List<Company>> {
        return enterpriseRepository.getFavoriteEnterprises()
    }
}