package com.example.domain.repository.db

import com.example.domain.model.ControlGRVStepBusiness
import kotlin.reflect.KClass

interface ControlGRVStepLocalRepository {
    // CREATE
    suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // READ
    suspend fun getLocalControlGRVStepByReference(reference: Int, type: KClass<out ControlGRVStepBusiness>): ControlGRVStepBusiness
    suspend fun getLocalControlGRVStepBySerialNumber(serialNumber: Int, type: KClass<out ControlGRVStepBusiness>): ControlGRVStepBusiness
    suspend fun checkIfControlGRVStepExist(controlGRVStepId: Int, type: KClass<out ControlGRVStepBusiness>): Boolean

    // UPDATE
    suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean

    // DELETE
    suspend fun deleteControlGRVStep(id: Int, type: KClass<out ControlGRVStepBusiness>): Boolean
}