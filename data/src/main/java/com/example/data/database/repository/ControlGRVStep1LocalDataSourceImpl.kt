package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVStep1Dao
import com.example.data.mapper.ControlGRVStepMapper
import com.example.domain.model.ControlGRVStepBusiness

class ControlGRVStep1LocalDataSourceImpl(private val controlGRVStep1Dao: ControlGRVStep1Dao) : ControlGRVStep1LocalDataSource {
    override suspend fun createLocalControlGRVStep(controlGRVStep1Local: ControlGRVStepBusiness): Boolean {
        controlGRVStep1Dao.insertControlGRVStep1(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal1(controlGRVStep1Local as ControlGRVStepBusiness.ControlGRVStep1)
        )
        return controlGRVStep1Dao.controlGRVStep1IdExist(controlGRVStep1Id = controlGRVStep1Local.reference)
    }

    override suspend fun findControlGRVStep1ByReference(reference: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep1LocalToBusiness(controlGRVStep1Local = controlGRVStep1Dao.findControlGRVStep1ByReference(reference = reference))
    }

    override suspend fun findControlGRVStep1BySerialNumber(serialNumber: Int): ControlGRVStepBusiness {
        return ControlGRVStepMapper.controlGRVStep1LocalToBusiness(controlGRVStep1Local = controlGRVStep1Dao.findControlGRVStep1BySerialNumber(serialNumber = serialNumber))
    }

    override suspend fun controlGRVStep1IdExist(controlGRVStep1Id: Int): Boolean {
        return controlGRVStep1Dao.controlGRVStep1IdExist(controlGRVStep1Id = controlGRVStep1Id)
    }

    override suspend fun updateControlGRVStep1(controlGRVStep1Local: ControlGRVStepBusiness): Boolean {
        controlGRVStep1Dao.updateControlGRVStep1(
            ControlGRVStepMapper.controlGRVStepBusinessToLocal1(controlGRVStep1Local as ControlGRVStepBusiness.ControlGRVStep1)
        )
        return controlGRVStep1IdExist(controlGRVStep1Id = controlGRVStep1Local.reference)
    }

    override suspend fun deleteControlGRVStep1ById(id: Int): Boolean {
        controlGRVStep1Dao.deleteControlGRVStep1ById(id = id)
        return !controlGRVStep1Dao.controlGRVStep1IdExist(controlGRVStep1Id = id)
    }
}