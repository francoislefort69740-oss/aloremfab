package com.example.data.repository

import com.example.data.database.repository.ControlGRVStep0LocalDataSource
import com.example.data.database.repository.ControlGRVStep1LocalDataSource
import com.example.data.database.repository.ControlGRVStep2LocalDataSource
import com.example.data.database.repository.ControlGRVStep3LocalDataSource
import com.example.data.database.repository.ControlGRVStep4LocalDataSource
import com.example.data.database.repository.ControlGRVStep5LocalDataSource
import com.example.data.database.repository.ControlGRVStep6LocalDataSource
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import kotlin.reflect.KClass

class ControlGRVStepRepositoryImpl(
    private val controlGRVStep0LocalDataSource: ControlGRVStep0LocalDataSource,
    private val controlGRVStep1LocalDataSource: ControlGRVStep1LocalDataSource,
    private val controlGRVStep2LocalDataSource: ControlGRVStep2LocalDataSource,
    private val controlGRVStep3LocalDataSource: ControlGRVStep3LocalDataSource,
    private val controlGRVStep4LocalDataSource: ControlGRVStep4LocalDataSource,
    private val controlGRVStep5LocalDataSource: ControlGRVStep5LocalDataSource,
    private val controlGRVStep6LocalDataSource: ControlGRVStep6LocalDataSource
): ControlGRVStepLocalRepository {
    override suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean = when (controlGRVStepBusiness) {
        is ControlGRVStepBusiness.ControlGRVStep0 -> controlGRVStep0LocalDataSource.createLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep1 -> controlGRVStep1LocalDataSource.createLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep2 -> controlGRVStep2LocalDataSource.createLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep3 -> controlGRVStep3LocalDataSource.createLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep4 -> controlGRVStep4LocalDataSource.createLocalControlGRVStep4(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep5 -> controlGRVStep5LocalDataSource.createLocalControlGRVStep5(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep6 -> controlGRVStep6LocalDataSource.createLocalControlGRVStep6(controlGRVStepBusiness = controlGRVStepBusiness)
    }

    override suspend fun getLocalControlGRVStepByReference(reference: Int, type: KClass<out ControlGRVStepBusiness>): ControlGRVStepBusiness = when(type) {
        ControlGRVStepBusiness.ControlGRVStep0::class -> controlGRVStep0LocalDataSource.getLocalControlGRVStepByReference(reference = reference)
        ControlGRVStepBusiness.ControlGRVStep1::class -> controlGRVStep1LocalDataSource.findControlGRVStep1ByReference(reference = reference)
        ControlGRVStepBusiness.ControlGRVStep2::class -> controlGRVStep2LocalDataSource.getLocalControlGRVStepByReference(reference = reference)
        ControlGRVStepBusiness.ControlGRVStep3::class -> controlGRVStep3LocalDataSource.getLocalControlGRVStepByReference(reference = reference)
        ControlGRVStepBusiness.ControlGRVStep4::class -> controlGRVStep4LocalDataSource.findControlGRVStep4ByReference(reference = reference)
        ControlGRVStepBusiness.ControlGRVStep5::class -> controlGRVStep5LocalDataSource.findControlGRVStep5ByReference(reference = reference)
        ControlGRVStepBusiness.ControlGRVStep6::class -> controlGRVStep6LocalDataSource.findControlGRVStep6ByReference(reference = reference)
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
        ControlGRVStepBusiness.ControlGRVStep2::class -> controlGRVStep2LocalDataSource.checkIfControlGRVStepExist(controlGRVStep2Id = controlGRVStepId)
        ControlGRVStepBusiness.ControlGRVStep3::class -> controlGRVStep3LocalDataSource.checkIfControlGRVStepExist(controlGRVStep2Id = controlGRVStepId)
        ControlGRVStepBusiness.ControlGRVStep4::class -> controlGRVStep4LocalDataSource.controlGRVStep4IdExist(controlGRVStepId = controlGRVStepId)
        ControlGRVStepBusiness.ControlGRVStep5::class -> controlGRVStep5LocalDataSource.controlGRVStep5IdExist(controlGRVStepId = controlGRVStepId)
        ControlGRVStepBusiness.ControlGRVStep6::class -> controlGRVStep6LocalDataSource.controlGRVStep6IdExist(controlGRVStepId = controlGRVStepId)
        else -> throw IllegalArgumentException("Invalid type")
    }

    override suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean = when (controlGRVStepBusiness){
        is ControlGRVStepBusiness.ControlGRVStep0 -> controlGRVStep0LocalDataSource.updateLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep1 -> controlGRVStep1LocalDataSource.updateControlGRVStep1(controlGRVStep1Local = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep2 -> controlGRVStep2LocalDataSource.updateLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep3 -> controlGRVStep3LocalDataSource.updateLocalControlGRVStep(controlGRVStepBusiness = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep4 -> controlGRVStep4LocalDataSource.updateControlGRVStep4(controlGRVStepLocal = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep5 -> controlGRVStep5LocalDataSource.updateControlGRVStep5(controlGRVStepLocal = controlGRVStepBusiness)
        is ControlGRVStepBusiness.ControlGRVStep6 -> controlGRVStep6LocalDataSource.updateControlGRVStep6(controlGRVStepLocal = controlGRVStepBusiness)
    }

    override suspend fun deleteControlGRVStep(id: Int, type: KClass<out ControlGRVStepBusiness>): Boolean = when(type) {
        ControlGRVStepBusiness.ControlGRVStep0::class -> controlGRVStep0LocalDataSource.deleteControlGRVStep(id = id)
        ControlGRVStepBusiness.ControlGRVStep1::class -> controlGRVStep1LocalDataSource.deleteControlGRVStep1ById(id = id)
        ControlGRVStepBusiness.ControlGRVStep2::class -> controlGRVStep2LocalDataSource.deleteLocalControlGRVStepById(id = id)
        ControlGRVStepBusiness.ControlGRVStep3::class -> controlGRVStep3LocalDataSource.deleteLocalControlGRVStepById(id = id)
        ControlGRVStepBusiness.ControlGRVStep4::class -> controlGRVStep4LocalDataSource.deleteControlGRVStep4ById(id = id)
        ControlGRVStepBusiness.ControlGRVStep5::class -> controlGRVStep5LocalDataSource.deleteControlGRVStep5ById(id = id)
        ControlGRVStepBusiness.ControlGRVStep6::class -> controlGRVStep6LocalDataSource.deleteControlGRVStep6ById(id = id)
        else -> throw IllegalArgumentException("Invalid type")
    }
}