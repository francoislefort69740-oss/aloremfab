package com.example.data.database.repository

import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStep5LocalDataSource {
    // CREATE
    suspend fun createLocalControlGRVStep5(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun findControlGRVStep5ByReference(reference: Int): ControlGRVStepBusiness
    suspend fun controlGRVStep5IdExist(controlGRVStepId: Int): Boolean

    // UPDATE
    suspend fun updateControlGRVStep5(controlGRVStepLocal: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteControlGRVStep5ById(id: Int): Boolean
}