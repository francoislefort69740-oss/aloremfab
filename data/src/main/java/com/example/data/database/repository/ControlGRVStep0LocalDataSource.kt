package com.example.data.database.repository

import com.example.data.database.entities.ControlGRVStep0Local
import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStep0LocalDataSource {
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