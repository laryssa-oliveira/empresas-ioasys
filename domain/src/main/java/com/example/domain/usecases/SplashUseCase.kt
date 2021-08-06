package com.example.domain.usecases

import com.example.domain.core.UseCase
import com.example.domain.repository.SplashRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SplashUseCase(scope: CoroutineScope, private val splashRepository: SplashRepository): UseCase<Boolean, Unit>(scope) {
    override fun run(params: Unit?): Flow<Boolean> {
        return splashRepository.isLogged()
    }
}