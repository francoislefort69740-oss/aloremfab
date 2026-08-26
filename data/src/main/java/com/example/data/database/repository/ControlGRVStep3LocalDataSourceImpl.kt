package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep3Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep3LocalDataSourceImpl(private val controlGRVStep3Dao: ControlGRVStep3Dao): ControlGRVStep3LocalDataSource {
    override suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep3Dao.insertControlGRVStep3(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal3(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep3)
        )
        return controlGRVStep3Dao.controlGRVStep3Exists(controlGRVStepBusiness.reference)
    }

    override suspend fun getLocalControlGRVStepByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep3LocalToBusiness(controlGRVStep3Local = controlGRVStep3Dao.getControlGRVStep3ByReference(reference))
    }

    override suspend fun checkIfControlGRVStepExist(controlGRVStep2Id: Int): Boolean {
        return controlGRVStep3Dao.controlGRVStep3Exists(controlGRVStep2Id)
    }

    override suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep3Dao.update(
            controlGRVStep3Local = ControlGRVStepMapper.controlGRVStepBusinessToLocal3(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep3)
        )
        return controlGRVStep3Dao.controlGRVStep3Exists(controlGRVStepBusiness.reference)
    }

    override suspend fun deleteLocalControlGRVStepById(id: Int): Boolean {
        controlGRVStep3Dao.deleteControlGRVStep3ById(id)
        return !controlGRVStep3Dao.controlGRVStep3Exists(id)
    }
}