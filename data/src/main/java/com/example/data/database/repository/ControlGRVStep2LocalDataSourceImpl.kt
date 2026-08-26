package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep2Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep2LocalDataSourceImpl(private val controlGRVStep2Dao: ControlGRVStep2Dao): ControlGRVStep2LocalDataSource {
    override suspend fun createLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep2Dao.insertControlGRVStep2(
            controlGRVStep2Local = ControlGRVStepMapper.controlGRVStepBusinessToLocal2(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep2)
        )
        return controlGRVStep2Dao.controlGRVStep2IdExist(controlGRVStep2Id = controlGRVStepBusiness.reference)
    }

    override suspend fun getLocalControlGRVStepByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep2LocalToBusiness(controlGRVStep2Local = controlGRVStep2Dao.findControlGRVStep2ByReference(reference = reference))
    }

    override suspend fun checkIfControlGRVStepExist(controlGRVStep2Id: Int): Boolean {
        return controlGRVStep2Dao.controlGRVStep2IdExist(controlGRVStep2Id = controlGRVStep2Id)
    }

    override suspend fun updateLocalControlGRVStep(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep2Dao.update(
            controlGRVStep2Local = ControlGRVStepMapper.controlGRVStepBusinessToLocal2(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep2)
        )
        return controlGRVStep2Dao.controlGRVStep2IdExist(controlGRVStep2Id = controlGRVStepBusiness.reference)
    }

    override suspend fun deleteLocalControlGRVStepById(id: Int): Boolean {
        controlGRVStep2Dao.deleteControlGRVStep2ById(id = id)
        return !controlGRVStep2Dao.controlGRVStep2IdExist(controlGRVStep2Id = id)
    }
}