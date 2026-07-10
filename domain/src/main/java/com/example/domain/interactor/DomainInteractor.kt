package com.example.domain.interactor

import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.UpdateUserUseCase

data class DomainInteractor(
    val getUserUseCase: GetUserUseCase,
    val updateUserUseCase: UpdateUserUseCase,
)
