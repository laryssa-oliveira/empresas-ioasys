package com.example.empresas.domain.usecases

import com.example.empresas.data.remote_data.core.ResultWrapper
import com.example.empresas.domain.core.UseCase
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.exceptions.EmptyFieldException
import com.example.empresas.domain.exceptions.MissingParamsException
import com.example.empresas.domain.repository.EnterpriseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class MainUseCase(scope: CoroutineScope, private val enterpriseRepository: EnterpriseRepository): UseCase<List<Company>, Unit>(scope){

    override fun run(params: Unit?): Flow<List<Company>> {
        return enterpriseRepository.getEnterprises()
    }

}