package com.example.domain.repository.db

import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStepLocalRepository {
    // CREATE
    suspend fun createLocalControlGRVStep0(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun getLocalControlGRVStep0ByReference(reference: Int): ControlGRVStepBusiness
    suspend fun getLocalControlGRVStep0BySerialNumber(serialNumber: Int): ControlGRVStepBusiness
    suspend fun checkIfControlGRVStep0Exist(controlGRVStep0Id: Int): Boolean

    // UPDATE
    suspend fun updateLocalControlGRVStep0(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteControlGRVStep0(id: Int): Boolean
}