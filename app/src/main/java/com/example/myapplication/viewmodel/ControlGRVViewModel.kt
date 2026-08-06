package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResultOf
import com.example.domain.interactor.DomainInteractor
import com.example.domain.model.ErrorBusiness
import com.example.domain.utils.GRVControlStepEnum
import com.example.myapplication.component.GRVControlStepTemplate
import com.example.myapplication.mapper.FrontControlGRCMapper
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.model.ControlGRVCheckPoint
import com.example.myapplication.model.StepControlGRV
import com.example.myapplication.utils.getCheckLogControlGRV
import kotlinx.coroutines.launch


class ControlGRVViewModel(interactor: DomainInteractor) : ViewModel() {

    private val getAllControlGRV = interactor.getAllControlGRVUseCase
    private val getCurrentlyGoingOnControlGRV = interactor.getCurrentlyGoingOnControlGRVUseCase
    private val getLoadedControlGRV = interactor.getLoadedControlGRVUseCase
    private val getUnLoadedControlGRV = interactor.getUnLoadedControlGRVUseCase
    private val createControlGRV = interactor.createControlGRVUseCase
    private val getControlGRV = interactor.getControlGRVUseCase
    private val deleteControlGRV = interactor.deleteControlGRVUseCase
    private val updateControlGRV = interactor.updateControlGRCUseCase
    private val updateLoadedControlGRVState = interactor.updateLoadedControlGRVStateUseCase
    private val getStepControlGrV = interactor.getControlGRVStepUseCase
    private val createStepControlGrV = interactor.createControlGRVStepUseCase
    private val checkSaveOrNextControlGRV = interactor.checkSaveOrNextControlGRVUseCase

    // LIVEDATA

    private val updateLoadedControlGRVStateLiveData = MutableLiveData<Pair<List<ControlGRV>, ControlGRV>>()
    private val getControlGRVLiveData = MutableLiveData<ControlGRV>()
    private val getAllControlGRVLiveData = MutableLiveData<List<ControlGRV>>()
    private val getCurrentlyGoingOnControlGRVLiveData = MutableLiveData<List<ControlGRV>>()
    private val getLoadedControlGRVLiveData = MutableLiveData<List<ControlGRV>>()
    private val injectExistingControlLiveData = MutableLiveData<Pair<List<ControlGRV>, ControlGRV>>()
    private val rejectExistingControlLiveData = MutableLiveData<Pair<List<ControlGRV>, ControlGRV>>()
    private val createControlGRVLiveData = MutableLiveData<Pair<List<ControlGRV>, ControlGRV>>()
    private val createStepAlsoNextControlGRVLiveData = MutableLiveData<Int>()
    private val updateControlGRVLiveData = MutableLiveData<String>()
    private val getStepControlGrVLiveData = MutableLiveData<StepControlGRV>()
    private val createStepControlGrVLiveData = MutableLiveData<StepControlGRV>()
    private val checkSaveOrNextControlGRVLiveData = MutableLiveData<Triple<Boolean, Boolean, Boolean>>()


    private val controlGRVNotFound = MutableLiveData<Boolean>()
    private val noControlGRVExist = MutableLiveData<Boolean>()
    private val controlGRVUidFieldEmpty = MutableLiveData<Boolean>()
    private val deleteControlGRVLiveData = MutableLiveData<List<ControlGRV>>()
    private val wrongSerialNumber = MutableLiveData<Boolean>()
    private val controlStepGrvNotInitialized = MutableLiveData<GRVControlStepEnum>()

    fun updateLoadedControlGRVStateLiveData() = updateLoadedControlGRVStateLiveData
    fun getControlGRVLiveData() = getControlGRVLiveData
    fun getAllControlGRVLiveData() = getAllControlGRVLiveData
    fun getCurrentlyGoingOnControlGRVLiveData() = getCurrentlyGoingOnControlGRVLiveData
    fun getLoadedControlGRVLiveData() = getLoadedControlGRVLiveData
    fun getPushListOfAddingPageLiveData() = injectExistingControlLiveData
    fun createControlGRVLiveData() = createControlGRVLiveData
    fun createStepAlsoNextControlGRVLiveData() = createStepAlsoNextControlGRVLiveData
    fun deleteControlGRVLiveData() = deleteControlGRVLiveData
    fun updateControlGRVLiveData() = updateControlGRVLiveData
    fun getStepControlGrVLiveData() = getStepControlGrVLiveData
    fun createStepControlGrVLiveData() = createStepControlGrVLiveData
    fun checkSaveOrNextControlGRVLiveData() = checkSaveOrNextControlGRVLiveData


    fun getControlStepGrvNotInitialized() = controlStepGrvNotInitialized
    fun getControlGRVNotFound() = controlGRVNotFound
    fun getNoControlGRVExist() = noControlGRVExist
    fun getControlGRVUidFieldEmpty() = controlGRVUidFieldEmpty
    fun wrongSerialNumber() = wrongSerialNumber

    // DATA

    private val _checkPoints = MutableLiveData<List<ControlGRVCheckPoint>>()
    val checkPoints: LiveData<List<ControlGRVCheckPoint>> = _checkPoints

    private val _allCheckPointsCompleted = MutableLiveData(false)
    val allCheckPointsCompleted: LiveData<Boolean> = _allCheckPointsCompleted

    private val _showCheckButton = MutableLiveData(false)
    val showCheckButton: LiveData<Boolean> = _showCheckButton

    private val _hasUnsavedChanges = MutableLiveData(false)
    val hasUnsavedChanges: LiveData<Boolean> = _hasUnsavedChanges

    private var initialCheckPoints: List<ControlGRVCheckPoint> = emptyList()

    fun loadTemplate(template: GRVControlStepTemplate) {
        val items = template.getStepRecyclerItem()
        _checkPoints.value = items
        initialCheckPoints = items.map  { it.duplicate() }
        _showCheckButton.value = items.any { it is ControlGRVCheckPoint.CheckBoxCheckPoint }
        updateState()
    }

    private fun updateState() {
        checkCompletion()
        checkUnsavedChanges()
    }

    private fun checkUnsavedChanges() {
        val current = _checkPoints.value ?: emptyList()
        _hasUnsavedChanges.value = current != initialCheckPoints
    }

    fun validateCurrentState() {
        initialCheckPoints =
            _checkPoints.value?.map { it.duplicate() } ?: emptyList()

        _hasUnsavedChanges.value = false
    }

    fun getResult(): List<ControlGRVCheckPoint> {
        return _checkPoints.value ?: emptyList()
    }

    private fun checkCompletion() {
        val completed = _checkPoints.value?.all { checkpoint ->
            when (checkpoint) {
                is ControlGRVCheckPoint.EditableCheckPoint ->
                    checkpoint.value.isNotBlank()
                is ControlGRVCheckPoint.CheckBoxCheckPoint ->
                    checkpoint.value != null
            }
        } ?: false

        getCheckLogControlGRV(list = _checkPoints.value, result = completed)

        _allCheckPointsCompleted.value = completed
    }

    fun checkAllCheckBoxes() {

        val updated = _checkPoints.value?.map { checkpoint ->
            if (checkpoint is ControlGRVCheckPoint.CheckBoxCheckPoint) checkpoint.copy(value = true, isChecked = true)
            else checkpoint
        } ?: emptyList()

        _checkPoints.value = updated

        checkCompletion()
    }

    fun onCheckPointChanged() {
        updateState()
    }


    // OBSERVATION

    fun checkSaveOrNextControlGRV(reference: Int, currentStep : GRVControlStepEnum){
        viewModelScope.launch {
            when (val result = checkSaveOrNextControlGRV.invoke(reference = reference, currentStep = currentStep)) {
                is ResultOf.Success -> checkSaveOrNextControlGRVLiveData.postValue(result.data)
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.ControlGRVStepNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun createStepControlGrV(step: StepControlGRV, controlGRV: ControlGRV) {
        viewModelScope.launch {
            when (val result = createStepControlGrV.invoke(
                step = FrontControlGRCMapper.controlGRVStepFrontToBusiness(stepControlGRV = step),
                controlGRVBusiness = FrontControlGRCMapper.controlGRVFrontToBusiness(controlGRV = controlGRV))) {
                is ResultOf.Success -> createStepControlGrVLiveData.postValue(FrontControlGRCMapper.controlGRVStepBusinessToFront(result.data))
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.ControlGRVStepNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun getStepControlGrv(reference: Int, stepNumber: GRVControlStepEnum) {
        viewModelScope.launch {
            when (val result = getStepControlGrV.invoke(reference = reference, stepNumber = stepNumber)) {
                is ResultOf.Success -> getStepControlGrVLiveData.postValue(FrontControlGRCMapper.controlGRVStepBusinessToFront(result.data))
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.ControlGRVStepNotFound -> controlGRVNotFound.postValue(true)
                    is ErrorBusiness.ControlGRVStepNotInitialized -> controlStepGrvNotInitialized.postValue(stepNumber)
                }
            }
        }
    }

    fun updateControlGRV(controlGRV: ControlGRV?, trigger: String) {
        viewModelScope.launch {
            val businessControl = controlGRV?.let { FrontControlGRCMapper.controlGRVFrontToBusiness(controlGRV = it) }
            when (val result = updateControlGRV.invoke(businessControl, trigger = trigger)){
                is ResultOf.Success -> updateControlGRVLiveData.postValue(trigger)
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun getControlGRV(id: Int) {
        viewModelScope.launch {
            when (val result = getControlGRV.invoke(id)){
                is ResultOf.Success -> getControlGRVLiveData.postValue(FrontControlGRCMapper.controlGRVBusinessToFront(result.data))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
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

    fun getAllCurrentlyControlGRV() {
        viewModelScope.launch {
            when (val result = getCurrentlyGoingOnControlGRV.invoke()){
                is ResultOf.Success -> getCurrentlyGoingOnControlGRVLiveData.postValue(FrontControlGRCMapper.allControlGRVBusinessToFront(result.data))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun pushControlGRV(controlGRV: ControlGRV, stepControlGRV: StepControlGRV) {
        viewModelScope.launch {
            when (val result = createControlGRV.invoke(
                controlGRVBusiness = FrontControlGRCMapper.controlGRVFrontToBusiness(controlGRV = controlGRV),
                controlGRVStepBusiness = FrontControlGRCMapper.controlGRVStepFrontToBusiness(stepControlGRV = stepControlGRV))){
                is ResultOf.Success -> createControlGRVLiveData.postValue(Pair(
                    FrontControlGRCMapper.allControlGRVBusinessToFront(result.data.first),
                    FrontControlGRCMapper.controlGRVBusinessToFront(result.data.second)))
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                    is ErrorBusiness.UserRegistrationFieldEmpty -> controlGRVUidFieldEmpty.postValue(true)
                    is ErrorBusiness.ControlGRVWrongSerialNumber -> wrongSerialNumber().postValue(true)
                }
            }
        }
    }

    fun pushAndNextControlGRV(controlGRV: ControlGRV, stepControlGRV: StepControlGRV) {
        viewModelScope.launch {
            when (val result = createControlGRV.invoke(
                controlGRVBusiness = FrontControlGRCMapper.controlGRVFrontToBusiness(controlGRV = controlGRV),
                controlGRVStepBusiness = FrontControlGRCMapper.controlGRVStepFrontToBusiness(
                    stepControlGRV = stepControlGRV
                )
            )) {
                is ResultOf.Success -> createStepAlsoNextControlGRVLiveData.postValue(result.data.second.serialNumber!!)
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                    is ErrorBusiness.UserRegistrationFieldEmpty -> controlGRVUidFieldEmpty.postValue(true)
                }
            }
        }
    }

    fun moveExistingControlToAddingPage(serialNumber: Int, state: Boolean){
        viewModelScope.launch {
            when (val result = updateLoadedControlGRVState.invoke(serialNumber = serialNumber, state = state)){
                is ResultOf.Success -> updateLoadedControlGRVStateLiveData.postValue(Pair(
                    FrontControlGRCMapper.allControlGRVBusinessToFront(result.data.first),
                    FrontControlGRCMapper.controlGRVBusinessToFront(result.data.second)))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun moveExistingControlToControlPage(serialNumber: Int, state: Boolean){
        viewModelScope.launch {
            when (val result = updateLoadedControlGRVState.invoke(serialNumber = serialNumber, state = state)){
                is ResultOf.Success -> injectExistingControlLiveData.postValue(Pair(
                    FrontControlGRCMapper.allControlGRVBusinessToFront(result.data.first),
                    FrontControlGRCMapper.controlGRVBusinessToFront(result.data.second)))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun getLoadedControls(){
        viewModelScope.launch {
            when (val result = getLoadedControlGRV.invoke()){
                is ResultOf.Success -> getLoadedControlGRVLiveData.postValue(FrontControlGRCMapper.allControlGRVBusinessToFront(result.data))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

    fun getUnloadedControls(){
        viewModelScope.launch {
            when (val result = getUnLoadedControlGRV.invoke()) {
                is ResultOf.Success -> getLoadedControlGRVLiveData.postValue(FrontControlGRCMapper.allControlGRVBusinessToFront(result.data))
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.NoControlGRVExist -> noControlGRVExist.postValue(true)
                    is ErrorBusiness.ControlGRVNotFound -> controlGRVNotFound.postValue(true)
                }
            }
        }
    }

}