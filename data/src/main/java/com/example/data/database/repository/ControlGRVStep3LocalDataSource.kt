package com.example.data.database.repository

import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStep3LocalDataSource {
    // CREATE
    suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun getLocalControlGRVStepByReference(reference: Int): ControlGRVStepBusiness
    suspend fun checkIfControlGRVStepExist(controlGRVStep2Id: Int): Boolean

    // UPDATE
    suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteLocalControlGRVStepById(id: Int): Boolean
}