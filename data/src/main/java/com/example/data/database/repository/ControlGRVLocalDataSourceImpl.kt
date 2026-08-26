package com.example.data.database.repository

import com.example.data.database.dao.ControlGRVDao
import com.example.data.mapper.ControlGRVMapper
import com.example.domain.model.ControlGRVBusiness

class ControlGRVLocalDataSourceImpl(private val controlGRVDao: ControlGRVDao): ControlGRVLocalDataSource {
    override suspend fun createLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int {
        controlGRVDao.insertControlGRV(ControlGRVMapper.controlGRVBusinessToLocal(controlGRVBusiness = controlGRVBusiness))
        return controlGRVDao.findControlGRVBySerialNumber(serialNumber = controlGRVBusiness.serialNumber!!).uid
    }

    override suspend fun getLocalControlGRVById(id: Int): ControlGRVBusiness {
        return ControlGRVMapper.controlGRVLocalToBusiness(controlGRVLocal = controlGRVDao.findControlGRVById(id = id))
    }

    override suspend fun getLocalControlGRVBySerialNumber(serialNumber: Int): ControlGRVBusiness {
        return ControlGRVMapper.controlGRVLocalToBusiness(controlGRVLocal = controlGRVDao.findControlGRVBySerialNumber(serialNumber = serialNumber))
    }

    override suspend fun checkIfControlGRVExist(controlGRVId: Int): Boolean {
        return controlGRVDao.controlGRVIdExist(controlGRVId = controlGRVId)
    }

    override suspend fun getAllControlGRV(): List<ControlGRVBusiness> {
        return try {
            ControlGRVMapper.allControlGRVLocalToBusiness(controlGRVLocals = controlGRVDao.getAll())
        } catch (e: Exception){
            emptyList()
        }
    }

    override suspend fun getCurrentlyGoingOn(): List<ControlGRVBusiness> {
        return try {
            ControlGRVMapper.allControlGRVLocalToBusiness(controlGRVLocals = controlGRVDao.getCurrentlyGoingOn())
        } catch (e: Exception){
            emptyList()
        }
    }

    override suspend fun getLoaded(): List<ControlGRVBusiness> {
        return try {
            ControlGRVMapper.allControlGRVLocalToBusiness(controlGRVLocals = controlGRVDao.getLoaded())
        } catch (e: Exception){
            emptyList()
        }
    }

    override suspend fun getUnLoaded(): List<ControlGRVBusiness> {
        return try {
            ControlGRVMapper.allControlGRVLocalToBusiness(controlGRVLocals = controlGRVDao.getUnLoaded())
        } catch (e: Exception){
            emptyList()
        }
    }

    override suspend fun updateLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int {
        controlGRVDao.update(ControlGRVMapper.controlGRVBusinessToLocal(controlGRVBusiness = controlGRVBusiness))
        return controlGRVDao.findControlGRVBySerialNumber(serialNumber = controlGRVBusiness.serialNumber!!).uid
    }

    override suspend fun deleteControlGRV(id: Int): Boolean {
        controlGRVDao.deleteControlGRVById(id = id)
        return !controlGRVDao.controlGRVIdExist(controlGRVId = id)
    }

    override suspend fun deleteAllControlGRV() {
        controlGRVDao.deleteAll()
    }
}