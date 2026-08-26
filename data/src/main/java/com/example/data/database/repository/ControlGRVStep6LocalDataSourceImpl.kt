package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep6Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep6LocalDataSourceImpl(private val controlGRVStep6Dao: ControlGRVStep6Dao) : ControlGRVStep6LocalDataSource {
    override suspend fun createLocalControlGRVStep6(controlGRVStepBusiness: ControlGRVStepBusiness): Boolean {
        controlGRVStep6Dao.insertControlGRVStep6(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal6(controlGRVStepBusiness as ControlGRVStepBusiness.ControlGRVStep6)
        )
        return controlGRVStep6Dao.controlGRVStep6IdExist(controlGRVStep6Id = controlGRVStepBusiness.reference)
    }

    override suspend fun findControlGRVStep6ByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep6LocalToBusiness(controlGRVStep6Local = controlGRVStep6Dao.findControlGRVStep6ByReference(reference = reference))
    }

    override suspend fun controlGRVStep6IdExist(controlGRVStepId: Int): Boolean {
        return controlGRVStep6Dao.controlGRVStep6IdExist(controlGRVStep6Id = controlGRVStepId)
    }

    override suspend fun updateControlGRVStep6(controlGRVStepLocal: ControlGRVStepBusiness): Boolean {
        controlGRVStep6Dao.updateControlGRVStep6(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal6(controlGRVStepLocal as ControlGRVStepBusiness.ControlGRVStep6)
        )
        return controlGRVStep6Dao.controlGRVStep6IdExist(controlGRVStep6Id = controlGRVStepLocal.reference)
    }

    override suspend fun deleteControlGRVStep6ById(id: Int): Boolean {
        controlGRVStep6Dao.deleteControlGRVStep6ById(id = id)
        return !controlGRVStep6Dao.controlGRVStep6IdExist(controlGRVStep6Id = id)
    }
}