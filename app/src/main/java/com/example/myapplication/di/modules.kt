package com.example.myapplication.di

import com.example.myapplication.viewmodel.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {  }

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
