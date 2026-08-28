package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResultOf
import com.example.domain.interactor.DomainInteractor
import com.example.domain.model.ErrorBusiness
import com.example.domain.utils.RETURN_TO_ADD_LIST_GRV_CONTROL
import com.example.myapplication.mapper.FrontControlGRCMapper
import com.example.myapplication.model.ControlGRV
import kotlinx.coroutines.launch

class ReportViewModel(interactor: DomainInteractor): ViewModel() {

    private val getAllFinishedControlGRV = interactor.getAllFinishedControlGRVUseCase
    private val getControlGRV = interactor.getControlGRVUseCase
    private val updateControlGRV = interactor.updateControlGRCUseCase
    private val deleteControlGRV = interactor.deleteControlGRVUseCase

    private val updateControlGRVLiveData = MutableLiveData<String>()
    private val noControlGRVExist = MutableLiveData<Boolean>()
    private val controlGRVNotFound = MutableLiveData<Boolean>()

    // LIVEDATA

    private val getAllFinishedControlGRVLiveData = MutableLiveData<List<ControlGRV>>()
    private val deleteControlGRVLiveData = MutableLiveData<List<ControlGRV>>()
    private val getControlGRVLiveData = MutableLiveData<ControlGRV>()


    fun getAllFinishedControlGRVLiveData() = getAllFinishedControlGRVLiveData
    fun deleteControlGRVLiveData() = deleteControlGRVLiveData
    fun getControlGRVLiveData() = getControlGRVLiveData
    fun updateControlGRVLiveData() = updateControlGRVLiveData

    // OBSERVATION

    fun getAllFinishedControlGRV() {
        viewModelScope.launch {
            when (val result = getAllFinishedControlGRV.invoke()) {
                is ResultOf.Success -> getAllFinishedControlGRVLiveData.postValue(
                    FrontControlGRCMapper.allControlGRVBusinessToFront(result.data)
                )
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                }
            }
        }
    }

    fun deleteControlGRV(id: Int) {
        viewModelScope.launch {
            when (val result = deleteControlGRV.invoke(id)){
                is ResultOf.Success -> deleteControlGRVLiveData.postValue(FrontControlGRCMapper.allControlGRVBusinessToFront(result.data))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun reloadControlGRV(id: Int) {
        viewModelScope.launch {
            when (val result = getControlGRV.invoke(id)){
                is ResultOf.Success -> {
                    result.data.currentlyGoingOn = true
                    getControlGRVLiveData.postValue(FrontControlGRCMapper.controlGRVBusinessToFront(result.data))
                }
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun updateControlGrc(controlGRV: ControlGRV) {
        viewModelScope.launch {
            when (val result = updateControlGRV.invoke(
                FrontControlGRCMapper.controlGRVFrontToBusiness(controlGRV = controlGRV),
                RETURN_TO_ADD_LIST_GRV_CONTROL)){
                is ResultOf.Success -> updateControlGRVLiveData.postValue(result.data)
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

}