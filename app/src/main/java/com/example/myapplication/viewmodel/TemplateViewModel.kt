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
    private val saveTemplateUseCase = interactor.saveTemplateUseCase
    private val getTemplateUseCase = interactor.getTemplateUseCase

    private val getAllTemplatesLiveData = MutableLiveData<List<TemplateGRV>>()
    private val saveTemplateLiveData = MutableLiveData<Boolean>()
    private val getTemplateLiveData = MutableLiveData<TemplateGRV>()
    private val noControlGRVExist = MutableLiveData<Boolean>()

    fun getAllTemplatesLiveData() = getAllTemplatesLiveData
    fun saveTemplateLiveData() = saveTemplateLiveData
    fun getTemplateLiveData() = getTemplateLiveData

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

    fun saveTemplate(context: Context, template: TemplateGRV) {
        android.util.Log.i("TemplateViewModel", "saveTemplate called for: ${template.name}")
        viewModelScope.launch {
            when (val result = saveTemplateUseCase.invoke(template = FrontControlGRCMapper.controlGRVTemplateFrontToBusiness(templateGRV = template), context = context)) {
                is ResultOf.Success -> {
                    android.util.Log.i("TemplateViewModel", "saveTemplate Success")
                    saveTemplateLiveData.postValue(result.data)
                }
                is ResultOf.Error -> {
                    android.util.Log.e("TemplateViewModel", "saveTemplate Error: ${result.exception}")
                    when(result.exception) {
                        is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    }
                }
            }
        }
    }

    fun getTemplate(context: Context, name: String) {
        viewModelScope.launch {
            when (val result = getTemplateUseCase.invoke(name = name, context = context)) {
                is ResultOf.Success -> getTemplateLiveData.postValue(
                    FrontControlGRCMapper.controlGRVTemplateBusinessToFront(result.data)
                )
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                }
            }
        }
    }
}