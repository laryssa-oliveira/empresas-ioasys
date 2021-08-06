package com.example.domain.usecases

import com.example.domain.core.UseCase
import com.example.domain.entities.Company
import com.example.domain.exceptions.MissingParamsException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FilterCompanyUseCase(scope: CoroutineScope): UseCase<MutableList<Company>, FilterCompanyUseCase.FilterCompanyParams>(scope) {
    data class FilterCompanyParams(val listCompany: MutableList<Company>, val term: String)

    override fun run(params: FilterCompanyParams?): Flow<MutableList<Company>> {
        return when {
            params == null -> throw MissingParamsException()
            params.term.isEmpty() -> flowOf(params.listCompany)
            else -> filterCompany(params.term, params.listCompany)
        }
    }

    private fun filterCompany(term: String, list: MutableList<Company>): Flow<MutableList<Company>> {
        val companies = list.filter { it.companyName.toLowerCase().contains(term.toLowerCase()) }.toMutableList()
        return flowOf(companies)
    }
}