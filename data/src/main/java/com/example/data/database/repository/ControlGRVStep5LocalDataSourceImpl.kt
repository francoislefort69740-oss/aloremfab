package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep5Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep5LocalDataSourceImpl(private val controlGRVStep5Dao: ControlGRVStep5Dao) : ControlGRVStep5LocalDataSource {
    override suspend fun createLocalControlGRVStep5(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep5Dao.insertControlGRVStep5(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal5(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep5)
        )
        return controlGRVStep5Dao.controlGRVStep5IdExist(controlGRVStep5Id = controlGRVStepBusiness.reference)
    }

    override suspend fun findControlGRVStep5ByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep5LocalToBusiness(controlGRVStep5Local = controlGRVStep5Dao.findControlGRVStep5ByReference(reference = reference))
    }

    override suspend fun controlGRVStep5IdExist(controlGRVStepId: Int): Boolean {
        return controlGRVStep5Dao.controlGRVStep5IdExist(controlGRVStep5Id = controlGRVStepId)
    }

    override suspend fun updateControlGRVStep5(controlGRVStepLocal: ControlGRVStepBusiness): Boolean {
        controlGRVStep5Dao.updateControlGRVStep5(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal5(controlGRVStepLocal as ControlGRVStepBusiness.ControlGRVStep5)
        )
        return controlGRVStep5Dao.controlGRVStep5IdExist(controlGRVStep5Id = controlGRVStepLocal.reference)
    }

    override suspend fun deleteControlGRVStep5ById(id: Int): Boolean {
        controlGRVStep5Dao.deleteControlGRVStep5ById(id = id)
        return !controlGRVStep5Dao.controlGRVStep5IdExist(controlGRVStep5Id = id)
    }
}