package com.example.data.database.repository

import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStep0LocalDataSource {
    // CREATE
    suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun getLocalControlGRVStepByReference(reference: Int): ControlGRVStepBusiness
    suspend fun getLocalControlGRVStepBySerialNumber(serialNumber: Int): ControlGRVStepBusiness
    suspend fun checkIfControlGRVStepExist(controlGRVStep0Id: Int): Boolean

    // UPDATE
    suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteControlGRVStep(id: Int): Boolean
}