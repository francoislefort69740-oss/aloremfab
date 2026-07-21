package com.example.domain.interactor

import com.example.domain.usecase.CreateControlGRVUseCase
import com.example.domain.usecase.CreateUserUseCase
import com.example.domain.usecase.DeleteUserUseCase
import com.example.domain.usecase.GetActiveIdUseCase
import com.example.domain.usecase.GetAllControlGRVUseCase
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.domain.usecase.GetControlGRVUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.UpdateActivateIdUseCase
import com.example.domain.usecase.UpdateUserUseCase

data class DomainInteractor(
    val getUserUseCase: GetUserUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val getAllUsersUseCase: GetAllUsersUseCase,
    val getActiveIdUseCase: GetActiveIdUseCase,
    val createUserUseCase: CreateUserUseCase,
    val updateActiveIdUseCase: UpdateActivateIdUseCase,
    val deleteUserUseCase: DeleteUserUseCase,
    val getControlGRVUseCase: GetControlGRVUseCase,
    val createControlGRVUseCase: CreateControlGRVUseCase,
    val getAllControlGRVUseCase: GetAllControlGRVUseCase
)
