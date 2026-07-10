package com.example.domain

import java.lang.Exception

sealed class ResultOf<out R> {
    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Error(val exception: Exception) : ResultOf<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}