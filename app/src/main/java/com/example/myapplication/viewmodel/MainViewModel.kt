package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResultOf
import com.example.domain.interactor.DomainInteractor
import com.example.domain.model.ErrorBusiness
import com.example.domain.model.UserBusiness
import com.example.myapplication.mapper.FrontUserMapper
import com.example.myapplication.model.User
import kotlinx.coroutines.launch

class MainViewModel(interactor: DomainInteractor): ViewModel() {

    private val getUser = interactor.getUserUseCase
    private val updateUser = interactor.updateUserUseCase
    private val getUsers = interactor.getAllUsersUseCase

    // LIVEDATA

    private val userLiveData = MutableLiveData<User>()
    private val updateUserLiveData = MutableLiveData<Boolean>()
    private val usersLiveData = MutableLiveData<List<User>>()


    fun getUserLiveData() = userLiveData
    fun updateUserLiveData() = updateUserLiveData
    fun getUsersLiveData() = usersLiveData


    // OBSERVATION

    fun getUser(id: Int){
        viewModelScope.launch {
            when(val result = getUser.invoke(id = id)) {
                is ResultOf.Success -> userLiveData.postValue(FrontUserMapper.userBusinessToFront(result.data))
                is  ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }

    fun updateUser(name: String, firstName: String, email: String){
        viewModelScope.launch {
            when(val result = updateUser.invoke(name = name, firstName = firstName, email = email)) {
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
                is ResultOf.Success -> usersLiveData.postValue(FrontUserMapper.allUsersBusinessToFront(result.data))
                is  ResultOf.Error -> when(result.exception) {
                    is ErrorBusiness.UserNotFound -> Log.i("TAG", "TAG")
                }
            }
        }
    }
}
