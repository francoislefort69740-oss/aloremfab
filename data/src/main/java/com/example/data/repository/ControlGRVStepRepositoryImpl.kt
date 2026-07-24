package com.example.data.repository

import com.example.data.database.repository.ControlGRVStep0LocalDataSource
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class ControlGRVStepRepositoryImpl(private val controlGRVStepLocalDataSource: ControlGRVStep0LocalDataSource): ControlGRVStepLocalRepository {
    override suspend fun createLocalControlGRVStep0(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        return controlGRVStepLocalDataSource.createLocalControlGRVStep0(controlGRVStepBusiness = controlGRVStepBusiness)
    }

    override suspend fun getLocalControlGRVStep0ByReference(reference: Int): ControlGRVStepBusiness {
        return controlGRVStepLocalDataSource.getLocalControlGRVStep0ByReference(reference = reference)
    }

    override suspend fun getLocalControlGRVStep0BySerialNumber(serialNumber: Int): ControlGRVStepBusiness {
        return controlGRVStepLocalDataSource.getLocalControlGRVStep0BySerialNumber(serialNumber = serialNumber)
    }

    override suspend fun checkIfControlGRVStep0Exist(controlGRVStep0Id: Int): Boolean {
        return controlGRVStepLocalDataSource.checkIfControlGRVStep0Exist(controlGRVStep0Id = controlGRVStep0Id)
    }

    override suspend fun updateLocalControlGRVStep0(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        return controlGRVStepLocalDataSource.updateLocalControlGRVStep0(controlGRVStepBusiness = controlGRVStepBusiness)
    }

    override suspend fun deleteControlGRVStep0(id: Int): Boolean {
        return controlGRVStepLocalDataSource.deleteControlGRVStep0(id = id)
    }
}