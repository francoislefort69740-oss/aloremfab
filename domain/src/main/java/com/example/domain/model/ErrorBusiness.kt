package com.example.domain.model

sealed class ErrorBusiness: Exception() {

    data object UserNotFound: ErrorBusiness() {
        private fun readResolve(): Any = UserNotFound
    }

    data object UserRegistrationFailed: ErrorBusiness() {
        private fun readResolve(): Any = UserRegistrationFailed
    }

    data object UserRegistrationFieldEmpty: ErrorBusiness() {
        private fun readResolve(): Any = UserRegistrationFieldEmpty
    }

}