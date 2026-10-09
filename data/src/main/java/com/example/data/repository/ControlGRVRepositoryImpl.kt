package com.example.data.repository

import com.example.data.api.repository.GetControlsRemoteDataSource
import com.example.data.database.repository.ControlGRVLocalDataSource
import com.example.domain.doIfFailure
import com.example.domain.doIfSuccess
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class ControlGRVRepositoryImpl(private val controlGRVLocalDataSource: ControlGRVLocalDataSource,
                               private val remoteDataSource: GetControlsRemoteDataSource): ControlGRVLocalRepository
{

    override suspend fun createLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int {
        return controlGRVLocalDataSource.createLocalControlGRV(controlGRVBusiness = controlGRVBusiness)
    }

    override suspend fun getLocalControlGRVById(id: Int): ControlGRVBusiness {
        return controlGRVLocalDataSource.getLocalControlGRVById(id = id)
    }

    override suspend fun getLocalControlGRVBySerialNumber(serialNumber: Int): ControlGRVBusiness {
        return controlGRVLocalDataSource.getLocalControlGRVBySerialNumber(serialNumber = serialNumber)
    }

    override suspend fun checkIfControlGRVExist(controlGRVId: Int): Boolean {
        return controlGRVLocalDataSource.checkIfControlGRVExist(controlGRVId = controlGRVId)
    }

    override suspend fun getCurrentlyGoingOn(): List<ControlGRVBusiness> {
        return controlGRVLocalDataSource.getCurrentlyGoingOn()
    }

    override suspend fun getAllControlGRV(): List<ControlGRVBusiness> {
        return controlGRVLocalDataSource.getAllControlGRV()
    }

    override suspend fun getAllRemoteAndLocalControlGRV(baseUrl: String): Pair<List<ControlGRVBusiness>, List<ControlGRVBusiness>> {
        val remote = remoteDataSource.getControlsFromApi(baseUrl = baseUrl)
        var remoteList = mutableListOf<ControlGRVBusiness>()

        remote.doIfSuccess { remoteList = it.list.toMutableList() }
        remote.doIfFailure { remoteList = emptyList<ControlGRVBusiness>().toMutableList() }

        return remoteList to controlGRVLocalDataSource.getAllControlGRV()
    }

    override suspend fun getLoaded(): List<ControlGRVBusiness> {
        return controlGRVLocalDataSource.getLoaded()
    }

    override suspend fun getUnloaded(): List<ControlGRVBusiness> {
        return controlGRVLocalDataSource.getUnLoaded()
    }

    override suspend fun updateLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int {
        return controlGRVLocalDataSource.updateLocalControlGRV(controlGRVBusiness = controlGRVBusiness)
    }

    override suspend fun deleteControlGRV(id: Int): Boolean {
        return controlGRVLocalDataSource.deleteControlGRV(id = id)
    }

    override suspend fun deleteAllControlGRV() {
        controlGRVLocalDataSource.deleteAllControlGRV()
    }
}