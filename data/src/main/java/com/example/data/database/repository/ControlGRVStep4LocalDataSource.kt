package com.example.data.database.repository

import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStep4LocalDataSource {
    // CREATE
    suspend fun createLocalControlGRVStep4(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun findControlGRVStep4ByReference(reference: Int): ControlGRVStepBusiness
    suspend fun controlGRVStep4IdExist(controlGRVStepId: Int): Boolean

    // UPDATE
    suspend fun updateControlGRVStep4(controlGRVStep1Local: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteControlGRVStep4ById(id: Int): Boolean
}