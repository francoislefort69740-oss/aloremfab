package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.ControlGRVMapper
import com.example.domain.ResultOf
import com.example.domain.interactor.DomainInteractor
import com.example.domain.model.ErrorBusiness
import com.example.myapplication.mapper.FrontControlGRCMapper
import com.example.myapplication.model.ControlGRV
import kotlinx.coroutines.launch


class ControlGRVViewModel(interactor: DomainInteractor) : ViewModel() {

    private val getAllControlGRV = interactor.getAllControlGRVUseCase
    private val createControlGRV = interactor.createControlGRVUseCase
    private val getControlGRV = interactor.getControlGRVUseCase
    private val deleteControlGRV = interactor.deleteControlGRVUseCase

    // DATAS

    private val controlsActivated = MutableLiveData< MutableList<Int>>()

    // LIVEDATA

    private val getAllControlGRVLiveData = MutableLiveData<List<ControlGRV>>()
    private val injectExistingControlLiveData = MutableLiveData<Pair<List<ControlGRV>, Int>>()
    private val createControlGRVLiveData = MutableLiveData<Boolean>()

    private val controlGRVNotFound = MutableLiveData<Boolean>()
    private val noControlGRVExist = MutableLiveData<Boolean>()
    private val controlGRVUidFieldEmpty = MutableLiveData<Boolean>()
    private val deleteControlGRVLiveData = MutableLiveData<List<ControlGRV>>()

    fun getAllControlGRVLiveData() = getAllControlGRVLiveData
    fun getUpdatedListOfAddingPageLiveData() = injectExistingControlLiveData
    fun createControlGRVLiveData() = createControlGRVLiveData
    fun deleteControlGRVLiveData() = deleteControlGRVLiveData

    fun getControlGRVNotFound() = controlGRVNotFound
    fun getNoControlGRVExist() = noControlGRVExist
    fun getControlGRVUidFieldEmpty() = controlGRVUidFieldEmpty


    // OBSERVATION

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

    fun getAllControlGRV() {
        viewModelScope.launch {
            when (val result = getAllControlGRV.invoke()){
                is ResultOf.Success -> getAllControlGRVLiveData.postValue(FrontControlGRCMapper.allControlGRVBusinessToFront(result.data))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun createControlGRV(controlGRV: ControlGRV) {
        viewModelScope.launch {
            when (val result = createControlGRV.invoke(FrontControlGRCMapper.controlGRVFrontToBusiness(controlGRV = controlGRV))){
                is ResultOf.Success -> createControlGRVLiveData.postValue(result.data)
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                    is ErrorBusiness.UserRegistrationFieldEmpty -> controlGRVUidFieldEmpty.postValue(true)
                }
            }
        }
    }

    fun injectExistingControl(serialNumber: Int){
        viewModelScope.launch {
            when (val result = getAllControlGRV.invoke()){
                is ResultOf.Success -> {
                    controlsActivated.value = (controlsActivated.value ?: mutableListOf()).apply { add(serialNumber) }
                    injectExistingControlLiveData.postValue(Pair(checkControlsActivated(FrontControlGRCMapper.allControlGRVBusinessToFront(result.data)), serialNumber))
                }
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun checkControlsActivated(controlsGRV: List<ControlGRV>) : List<ControlGRV> {
        val list = controlsGRV.toMutableList()
        controlsActivated.value?.forEach { activated -> list.removeIf { it.serialNumber == activated } }
        return list.toList()
    }






}