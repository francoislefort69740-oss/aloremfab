package com.example.data.database.repository

import com.example.domain.model.ControlGRVStepBusiness

interface ControlGRVStep6LocalDataSource {
    // CREATE
    suspend fun createLocalControlGRVStep6(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun findControlGRVStep6ByReference(reference: Int): ControlGRVStepBusiness
    suspend fun controlGRVStep6IdExist(controlGRVStepId: Int): Boolean

    // UPDATE
    suspend fun updateControlGRVStep6(controlGRVStepLocal: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteControlGRVStep6ById(id: Int): Boolean
}