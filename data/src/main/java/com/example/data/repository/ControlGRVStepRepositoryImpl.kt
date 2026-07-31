package com.example.data.repository

import com.example.data.database.repository.ControlGRVStep0LocalDataSource
import com.example.data.database.repository.ControlGRVStep1LocalDataSource
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import kotlin.reflect.KClass

class ControlGRVStepRepositoryImpl(
    private val controlGRVStep0LocalDataSource: ControlGRVStep0LocalDataSource,
    private val controlGRVStep1LocalDataSource: ControlGRVStep1LocalDataSource
): ControlGRVStepLocalRepository {
    override suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean = when (controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 -> controlGRVStep0LocalDataSource.createLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep1 -> controlGRVStep1LocalDataSource.createLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
    }

    override suspend fun getLocalControlGRVStepByReference(reference: Int, type: KClass<out ControlGRVStepBusiness>): ControlGRVStepBusiness = when(type) {
        ControlGRVStepBusiness.ControlGRVStep0::class -> controlGRVStep0LocalDataSource.getLocalControlGRVStepByReference(reference = reference)
        ControlGRVStepBusiness.ControlGRVStep1::class -> controlGRVStep1LocalDataSource.findControlGRVStep1ByReference(reference = reference)
        else -> throw IllegalArgumentException("Invalid type")
    }

    override suspend fun getLocalControlGRVStepBySerialNumber(serialNumber: Int, type: KClass<out ControlGRVStepBusiness>): ControlGRVStepBusiness = when(type) {
        ControlGRVStepBusiness.ControlGRVStep0::class -> controlGRVStep0LocalDataSource.getLocalControlGRVStepBySerialNumber(serialNumber = serialNumber)
        ControlGRVStepBusiness.ControlGRVStep1::class -> controlGRVStep1LocalDataSource.findControlGRVStep1BySerialNumber(serialNumber = serialNumber)
        else -> throw IllegalArgumentException("Invalid type")
    }

    override suspend fun checkIfControlGRVStepExist(controlGRVStepId: Int, type: KClass<out ControlGRVStepBusiness>): Boolean = when(type) {
        ControlGRVStepBusiness.ControlGRVStep0::class -> controlGRVStep0LocalDataSource.checkIfControlGRVStepExist(controlGRVStep0Id = controlGRVStepId)
        ControlGRVStepBusiness.ControlGRVStep1::class -> controlGRVStep1LocalDataSource.controlGRVStep1IdExist(controlGRVStep1Id = controlGRVStepId)
        else -> throw IllegalArgumentException("Invalid type")
    }

    override suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean = when (controlGRVStepBusiness){
        is ControlGRVStepBusiness.ControlGRVStep0 -> controlGRVStep0LocalDataSource.updateLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep1 -> controlGRVStep1LocalDataSource.updateControlGRVStep1(controlGRVStep1Local = controlGRVStepBusiness)
    }

    override suspend fun deleteControlGRVStep(id: Int, type: KClass<out ControlGRVStepBusiness>): Boolean = when(type) {
        ControlGRVStepBusiness.ControlGRVStep0::class -> controlGRVStep0LocalDataSource.deleteControlGRVStep(id = id)
        ControlGRVStepBusiness.ControlGRVStep1::class -> controlGRVStep1LocalDataSource.deleteControlGRVStep1ById(id = id)
        else -> throw IllegalArgumentException("Invalid type")
    }
}