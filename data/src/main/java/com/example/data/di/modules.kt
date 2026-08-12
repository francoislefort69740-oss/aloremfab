package com.example.data.di

import com.example.data.database.MyDatabase
import com.example.data.database.repository.ActiveIdLocalDataSource
import com.example.data.database.repository.ActiveIdLocalDataSourceImpl
import com.example.data.database.repository.ControlGRVLocalDataSource
import com.example.data.database.repository.ControlGRVLocalDataSourceImpl
import com.example.data.database.repository.ControlGRVStep0LocalDataSource
import com.example.data.database.repository.ControlGRVStep0LocalDataSourceImpl
import com.example.data.database.repository.ControlGRVStep1LocalDataSource
import com.example.data.database.repository.ControlGRVStep1LocalDataSourceImpl
import com.example.data.database.repository.ControlGRVStep2LocalDataSource
import com.example.data.database.repository.ControlGRVStep2LocalDataSourceImpl
import com.example.data.database.repository.ControlGRVStep3LocalDataSource
import com.example.data.database.repository.ControlGRVStep3LocalDataSourceImpl
import com.example.data.database.repository.UserLocalDataSource
import com.example.data.database.repository.UserLocalDataSourceImpl
import com.example.data.repository.ActiveIdRepositoryImpl
import com.example.data.repository.ControlGRVRepositoryImpl
import com.example.data.repository.ControlGRVStepRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.repository.db.UserLocalRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    // DATABASE
    single { MyDatabase.getDatabase(androidApplication()) }

    factory { get<MyDatabase>().userDao() }
    factory { get<MyDatabase>().activeIdDao() }
    factory { get<MyDatabase>().controlGRVDao() }
    factory { get<MyDatabase>().controlGRVStep0Dao() }
    factory { get<MyDatabase>().controlGRVStep1Dao() }
    factory { get<MyDatabase>().controlGRVStep2Dao() }
    factory { get<MyDatabase>().controlGRVStep3Dao() }

    factory<UserLocalRepository> { UserRepositoryImpl(get()) }
    factory<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
    factory<ActiveIdLocalDataSource> { ActiveIdLocalDataSourceImpl(get()) }
    factory<ActiveIdLocalRepository> { ActiveIdRepositoryImpl(get()) }
    factory<ControlGRVLocalDataSource> { ControlGRVLocalDataSourceImpl(get()) }
    factory<ControlGRVLocalRepository> { ControlGRVRepositoryImpl(get()) }

    factory<ControlGRVStep0LocalDataSource> { ControlGRVStep0LocalDataSourceImpl(get()) }
    factory<ControlGRVStep1LocalDataSource> { ControlGRVStep1LocalDataSourceImpl(get()) }
    factory<ControlGRVStep2LocalDataSource> { ControlGRVStep2LocalDataSourceImpl(get()) }
    factory<ControlGRVStep3LocalDataSource> { ControlGRVStep3LocalDataSourceImpl(get()) }
    factory<ControlGRVStepLocalRepository> {
        ControlGRVStepRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    }

}