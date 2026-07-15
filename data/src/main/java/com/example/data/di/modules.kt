package com.example.data.di

import com.example.data.database.MyDatabase
import com.example.data.database.repository.ActiveIdLocalDataSource
import com.example.data.database.repository.ActiveIdLocalDataSourceImpl
import com.example.data.database.repository.UserLocalDataSource
import com.example.data.database.repository.UserLocalDataSourceImpl
import com.example.data.repository.ActiveIdRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.db.ActiveIdLocalRepository
import com.example.domain.repository.db.UserLocalRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    // DATABASE
    single { MyDatabase.getDatabase(androidApplication()) }

    factory { get<MyDatabase>().userDao() }
    factory { get<MyDatabase>().activeIdDao() }

    factory<UserLocalRepository> { UserRepositoryImpl(get()) }
    factory<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
    factory<ActiveIdLocalDataSource> { ActiveIdLocalDataSourceImpl(get()) }
    factory<ActiveIdLocalRepository> { ActiveIdRepositoryImpl(get()) }
}