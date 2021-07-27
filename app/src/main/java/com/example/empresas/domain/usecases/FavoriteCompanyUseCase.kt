package com.example.empresas.domain.usecases

import com.example.empresas.data.cache_data.database.EmpresasDatabase
import com.example.empresas.domain.core.UseCase
import com.example.empresas.domain.entities.Company
import com.example.empresas.domain.exceptions.MissingParamsException
import com.example.empresas.domain.repository.EnterpriseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FavoriteCompanyUseCase(scope: CoroutineScope, private val enterpriseRepository: EnterpriseRepository) :
    UseCase< Boolean, FavoriteCompanyUseCase.FavoriteCompanyParams>(scope) {
    data class FavoriteCompanyParams(val like: Boolean, val company: Company)

    override fun run(params: FavoriteCompanyParams?): Flow<Boolean> {
        return when {
            params == null -> throw MissingParamsException()
            else -> enterpriseRepository.favoriteCompany(params.like, params.company)
        }
    }


}
