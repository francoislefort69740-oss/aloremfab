package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep4Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep4LocalDataSourceImpl(private val controlGRVStep4Dao: ControlGRVStep4Dao) : ControlGRVStep4LocalDataSource {
    override suspend fun createLocalControlGRVStep4(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep4Dao.insertControlGRVStep4(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal4(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep4)
        )
        return controlGRVStep4Dao.controlGRVStep4IdExist(controlGRVStep4Id = controlGRVStepBusiness.reference)
    }

    override suspend fun findControlGRVStep4ByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep4LocalToBusiness(controlGRVStep4Local = controlGRVStep4Dao.findControlGRVStep4ByReference(reference = reference))
    }

    override suspend fun controlGRVStep4IdExist(controlGRVStepId: Int): Boolean {
        return controlGRVStep4Dao.controlGRVStep4IdExist(controlGRVStep4Id = controlGRVStepId)
    }

    override suspend fun updateControlGRVStep4(controlGRVStepLocal: ControlGRVStepBusiness): Boolean {
        controlGRVStep4Dao.updateControlGRVStep4(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal4(controlGRVStepLocal as ControlGRVStepBusiness.ControlGRVStep4)
        )
        return controlGRVStep4Dao.controlGRVStep4IdExist(controlGRVStep4Id = controlGRVStepLocal.reference)
    }

    override suspend fun deleteControlGRVStep4ById(id: Int): Boolean {
        controlGRVStep4Dao.deleteControlGRVStep4ById(id = id)
        return !controlGRVStep4Dao.controlGRVStep4IdExist(controlGRVStep4Id = id)
    }
}