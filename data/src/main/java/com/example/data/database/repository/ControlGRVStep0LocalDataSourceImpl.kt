package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep0Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep0LocalDataSourceImpl(private val controlGRVStep0Dao: ControlGRVStep0Dao): ControlGRVStep0LocalDataSource {

    override suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep0Dao.insertControlGRVStep0(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal0(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0)
        )
        return checkIfControlGRVStepExist(controlGRVStep0Id = controlGRVStepBusiness.reference)
    }

    override suspend fun getLocalControlGRVStepByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep0LocalToBusiness(controlGRVStep0Local = controlGRVStep0Dao.findControlGRVStep0ByReference(reference = reference))
    }

    override suspend fun getLocalControlGRVStepBySerialNumber(serialNumber: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep0LocalToBusiness(controlGRVStep0Local = controlGRVStep0Dao.findControlGRVStep0BySerialNumber(serialNumber = serialNumber))
    }

    override suspend fun checkIfControlGRVStepExist(controlGRVStep0Id: Int): Boolean {
        return controlGRVStep0Dao.controlGRVStep0IdExist(controlGRVStep0Id = controlGRVStep0Id)
    }

    override suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep0Dao.update(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal0(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0)
        )
        return checkIfControlGRVStepExist(controlGRVStep0Id = controlGRVStepBusiness.reference)
    }

    override suspend fun deleteControlGRVStep(id: Int): Boolean {
        controlGRVStep0Dao.deleteControlGRVStep0ById(id = id)
        return !controlGRVStep0Dao.controlGRVStep0IdExist(controlGRVStep0Id = id)
    }

}