package com.example.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResultOf
import com.example.domain.interactor.DomainInteractor
import com.example.domain.model.ErrorBusiness
import com.example.myapplication.mapper.FrontControlGRCMapper
import com.example.myapplication.model.TemplateGRV
import kotlinx.coroutines.launch

class TemplateViewModel(interactor: DomainInteractor): ViewModel() {
    private val getAllTemplatesUseCase = interactor.getAllTemplatesUseCase

    private val getAllTemplatesLiveData = MutableLiveData<List<TemplateGRV>>()
    private val noControlGRVExist = MutableLiveData<Boolean>()

    fun getAllTemplatesLiveData() = getAllTemplatesLiveData

    // OBSERVATION

    fun getAllTemplates(context: Context) {
        viewModelScope.launch {
            when (val result = getAllTemplatesUseCase.invoke(context = context)) {
                is ResultOf.Success -> getAllTemplatesLiveData.postValue(
                    FrontControlGRCMapper.getAllControlGRVBusinessToFront(result.data)
                )
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                }
            }
        }
    }
}