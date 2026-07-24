package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep0Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep0LocalDataSourceImpl(private val controlGRVStep0Dao: ControlGRVStep0Dao): ControlGRVStep0LocalDataSource {

    override suspend fun createLocalControlGRVStep0(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep0Dao.insertControlGRVStep0(ControlGRVStepMapper.controlGRVStepBusinessToLocal0(controlGRVStepBusiness))
        return checkIfControlGRVStep0Exist(controlGRVStep0Id = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).reference)
    }

    override suspend fun getLocalControlGRVStep0ByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep0LocalToBusiness(controlGRVStep0Local = controlGRVStep0Dao.findControlGRVStep0ByReference(reference = reference))
    }

    override suspend fun getLocalControlGRVStep0BySerialNumber(serialNumber: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep0LocalToBusiness(controlGRVStep0Local = controlGRVStep0Dao.findControlGRVStep0BySerialNumber(serialNumber = serialNumber))
    }

    override suspend fun checkIfControlGRVStep0Exist(controlGRVStep0Id: Int): Boolean {
        return controlGRVStep0Dao.controlGRVStep0IdExist(controlGRVStep0Id = controlGRVStep0Id)
    }

    override suspend fun updateLocalControlGRVStep0(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        return checkIfControlGRVStep0Exist(controlGRVStep0Id = (controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep0).reference)
    }

    override suspend fun deleteControlGRVStep0(id: Int): Boolean {
        controlGRVStep0Dao.deleteControlGRVStep0ById(id = id)
        return !controlGRVStep0Dao.controlGRVStep0IdExist(controlGRVStep0Id = id)
    }

}