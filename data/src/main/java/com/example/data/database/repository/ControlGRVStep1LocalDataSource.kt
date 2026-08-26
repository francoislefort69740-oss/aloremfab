package com.example.data.database.repository

import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStep1LocalDataSource {
    // CREATE
    suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun findControlGRVStep1ByReference(reference: Int): ControlGRVStepBusiness
    suspend fun findControlGRVStep1BySerialNumber(serialNumber: Int): ControlGRVStepBusiness
    suspend fun controlGRVStep1IdExist(controlGRVStep1Id: Int): Boolean

    // UPDATE
    suspend fun updateControlGRVStep1(controlGRVStep1Local: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteControlGRVStep1ById(id: Int): Boolean
}