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
    private val updateActiveId = interactor.updateActiveIdUseCase
    private val createUser = interactor.createUserUseCase
    private val deleteUser = interactor.deleteUserUseCase

    // LIVEDATA

    private val userLiveData = MutableLiveData<User>()
    private val updateUserLiveData = MutableLiveData<Boolean>()
    private val usersLiveData = MutableLiveData<List<User>>()
    private val activeUserLiveData = MutableLiveData<User>()
    private val createUserLiveData = MutableLiveData<Boolean>()
    private val updateActiveIdLiveData = MutableLiveData<List<User>>()
    private val deleteUserLiveData = MutableLiveData<List<User>>()
    private val getActiveIdLiveData = MutableLiveData<Int>()


    private val userRegistrationError = MutableLiveData<Boolean>()
    private val userRegistrationNameError = MutableLiveData<Boolean>()
    private val userRegistrationForNameError = MutableLiveData<Boolean>()
    private val userRegistrationEmailError = MutableLiveData<Boolean>()
    private val noUserExist = MutableLiveData<Boolean>()


    fun getUserLiveData() = userLiveData
    fun updateUserLiveData() = updateUserLiveData
    fun getUsersLiveData() = usersLiveData
    fun createUserLiveData() = createUserLiveData
    fun getActiveUserLiveData() = activeUserLiveData
    fun updateActiveIdLiveData() = updateActiveIdLiveData
    fun deleteUserLiveData() = deleteUserLiveData
    fun getActiveIdLiveData() = getActiveIdLiveData

    // Error LiveData
    fun getUserRegistrationError() = userRegistrationError
    fun getUserRegistrationNameError() = userRegistrationNameError
    fun getUserRegistrationForNameError() = userRegistrationForNameError
    fun getUserRegistrationEmailError() = userRegistrationEmailError
    fun getNoUserExist() = noUserExist


    // OBSERVATION

    fun deleteUser(uid: Int, users: List<User>){
        viewModelScope.launch {
            when(val result = deleteUser.invoke(uid = uid)){
                is ResultOf.Success -> deleteUserLiveData.postValue(FrontUserMapper.allUsersBusinessToFront(result.data))
                is ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }

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
                    is ErrorBusiness.UserRegistrationNameFieldEmpty -> userRegistrationNameError.postValue(true)
                    is ErrorBusiness.UserRegistrationForNameFieldEmpty -> userRegistrationForNameError.postValue(true)
                    is ErrorBusiness.UserRegistrationEmailFieldEmpty -> userRegistrationEmailError.postValue(true)
                }
            }
        }
    }

    fun getUsers(){
        viewModelScope.launch {
            when(val result = getUsers.invoke()) {
                is ResultOf.Success -> usersLiveData.postValue(FrontUserMapper.allUsersBusinessToFront(result.data))
                is  ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }

    fun getActiveUser(users: List<User>){
        viewModelScope.launch {
            activeUserLiveData.postValue(users.find { it.isActive } ?: User(uid = 0, firstName = "", lastName = "", email = ""))
        }
    }

    fun updateId(activeId: Int, users: List<User>){
        viewModelScope.launch {
            when (val result = updateActiveId.invoke(activeId = activeId)) {
                is ResultOf.Success -> {
                    users.forEach { it.isActive = it.uid == activeId }
                    updateActiveIdLiveData.postValue(users)
                }
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }

    fun getActiveId() {
        viewModelScope.launch {
            when (val result = getActiveId.invoke(0)) {
                is ResultOf.Success -> getActiveIdLiveData.postValue(result.data.activeId)
                is ResultOf.Error -> when (result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }
}
