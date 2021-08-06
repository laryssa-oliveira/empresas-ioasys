package com.example.domain.usecases

import com.example.domain.core.UseCase
import com.example.domain.entities.Company
import com.example.domain.exceptions.MissingParamsException
import com.example.domain.repository.EnterpriseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class FavoriteCompanyUseCase(scope: CoroutineScope, private val enterpriseRepository: EnterpriseRepository) :
    UseCase<Boolean, FavoriteCompanyUseCase.FavoriteCompanyParams>(scope) {
    data class FavoriteCompanyParams(val like: Boolean, val company: Company)

    override fun run(params: FavoriteCompanyParams?): Flow<Boolean> {
        return when {
            params == null -> throw MissingParamsException()
            else -> enterpriseRepository.favoriteCompany(params.like, params.company)
        }
    }


}
