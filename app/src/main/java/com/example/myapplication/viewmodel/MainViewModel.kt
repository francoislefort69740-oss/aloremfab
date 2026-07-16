package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResultOf
import com.example.domain.interactor.DomainInteractor
import com.example.domain.model.ErrorBusiness
import com.example.myapplication.mapper.FrontUserMapper
import com.example.myapplication.model.User
import kotlinx.coroutines.launch

class MainViewModel(interactor: DomainInteractor): ViewModel() {

    private val getUser = interactor.getUserUseCase
    private val updateUser = interactor.updateUserUseCase
    private val getUsers = interactor.getAllUsersUseCase
    private val getActiveId = interactor.getActiveIdUseCase
    private val createUser = interactor.createUserUseCase

    // LIVEDATA

    private val userLiveData = MutableLiveData<User>()
    private val updateUserLiveData = MutableLiveData<Boolean>()
    private val usersLiveData = MutableLiveData< Pair<List<User>, Int>>()
    private val activeIdLiveData = MutableLiveData<Int>()
    private val createUserLiveData = MutableLiveData<Boolean>()


    private val userRegistrationError = MutableLiveData<Boolean>()
    private val userRegistrationNameError = MutableLiveData<Boolean>()
    private val userRegistrationForNameError = MutableLiveData<Boolean>()
    private val userRegistrationEmailError = MutableLiveData<Boolean>()
    private val noUserExist = MutableLiveData<Boolean>()


    fun getUserLiveData() = userLiveData
    fun updateUserLiveData() = updateUserLiveData
    fun getUsersLiveData() = usersLiveData
    fun createUserLiveData() = createUserLiveData

    // Error LiveData
    fun getUserRegistrationError() = userRegistrationError
    fun getUserRegistrationNameError() = userRegistrationNameError
    fun getUserRegistrationForNameError() = userRegistrationForNameError
    fun getUserRegistrationEmailError() = userRegistrationEmailError
    fun getNoUserExist() = noUserExist


    // OBSERVATION

    fun createUser(name: String, firstName: String, email: String){
        viewModelScope.launch {
            when(val result = createUser.invoke(name = name, firstName = firstName, email = email)) {
                is ResultOf.Success -> createUserLiveData.postValue(result.data)
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> userRegistrationError.postValue(true)
                    is ErrorBusiness.UserRegistrationNameFieldEmpty -> userRegistrationNameError.postValue(true)
                    is ErrorBusiness.UserRegistrationForNameFieldEmpty -> userRegistrationForNameError.postValue(true)
                    is ErrorBusiness.UserRegistrationEmailFieldEmpty -> userRegistrationEmailError.postValue(true)
                }
            }
        }
    }

    fun getUser(id: Int){
        viewModelScope.launch {
            when(val result = getUser.invoke(id = id)) {
                is ResultOf.Success -> userLiveData.postValue(FrontUserMapper.userBusinessToFront(result.data))
                is  ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.NoUserExist -> noUserExist.postValue(true)
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }

    fun updateUser(name: String, firstName: String, email: String, uid: Int){
        viewModelScope.launch {
            when(val result = updateUser.invoke(name = name, firstName = firstName, email = email, uid = uid)) {
                is ResultOf.Success -> updateUserLiveData.postValue(result.data)
                is  ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }

    fun getUsers(){
        viewModelScope.launch {
            when(val result = getUsers.invoke()) {
                is ResultOf.Success -> usersLiveData.postValue(Pair(FrontUserMapper.allUsersBusinessToFront(result.data.first), result.data.second))
                is  ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }

    fun getActiveId(id: Int){
        viewModelScope.launch {
            when(val result = getActiveId.invoke(id = id)) {
                is ResultOf.Success -> activeIdLiveData.postValue(result.data.activeId)
                is  ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }
}
