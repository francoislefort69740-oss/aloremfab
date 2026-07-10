package com.example.myapplication

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.myapplication.di.appModule
import com.example.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule,
                domainModule,
                dataModule,
                viewModelModule
            )
        }
    }
}