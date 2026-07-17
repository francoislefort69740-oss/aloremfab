package com.example.myapplication.model

data class User(
    val uid: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    var isActive: Boolean = false
)
