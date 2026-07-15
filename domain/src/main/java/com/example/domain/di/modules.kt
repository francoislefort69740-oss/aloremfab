package com.example.domain.di

import com.example.domain.interactor.DomainInteractor
import com.example.domain.usecase.CreateUserUseCase
import com.example.domain.usecase.GetActiveIdUseCase
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.UpdateUserUseCase
import org.koin.dsl.module

val domainModule = module {

    single { DomainInteractor(get(), get(), get(), get(), get()) }

    single { UpdateUserUseCase(get()) }
    single { GetUserUseCase(get()) }
    single { GetAllUsersUseCase(get(), get()) }
    single { GetActiveIdUseCase(get()) }
    single { CreateUserUseCase(get()) }
}