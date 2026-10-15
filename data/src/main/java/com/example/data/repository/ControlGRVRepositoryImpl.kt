package com.example.data.repository

import com.example.data.api.repository.CreateControlRemoteDataSource
import com.example.data.api.repository.DeleteControlRemoteDataSource
import com.example.data.api.repository.GetControlsRemoteDataSource
import com.example.data.api.repository.UpdateControlRemoteDataSource
import com.example.data.database.repository.ControlGRVLocalDataSource
import com.example.domain.doIfFailure
import com.example.domain.doIfSuccess
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.RemoteMessageBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository

class ControlGRVRepositoryImpl(private val controlGRVLocalDataSource: ControlGRVLocalDataSource,
                               private val getRemoteDataSource: GetControlsRemoteDataSource,
                               private val createRemoteDataSource: CreateControlRemoteDataSource,
                               private val updateRemoteDataSource: UpdateControlRemoteDataSource,
                               private val deleteRemoteDataSource: DeleteControlRemoteDataSource
    ): ControlGRVLocalRepository
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
        val remote = getRemoteDataSource.getControlsFromApi(baseUrl = baseUrl)
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

    override suspend fun createRemoteControlGRV(controlGRVBusiness: ControlGRVBusiness, baseUrl: String, allSteps: ControlGRVStepBusiness.ControlGRVAllStep): RemoteMessageBusiness {
        val remote = createRemoteDataSource.createControl(control = controlGRVBusiness, baseUrl = baseUrl, allSteps = allSteps)
        var message = ""

        remote.doIfSuccess { message = it.message }
        remote.doIfFailure { message = it.localizedMessage ?: it.message ?: "Erreur lors de la création distante" }

        return RemoteMessageBusiness(message = message)
    }

    override suspend fun updateRemoteControlGRV(id: Int, controlGRVBusiness: ControlGRVBusiness, baseUrl: String, allSteps: ControlGRVStepBusiness.ControlGRVAllStep): RemoteMessageBusiness {
        val remote = updateRemoteDataSource.updateControl(control = controlGRVBusiness, baseUrl = baseUrl, id = id, allSteps = allSteps)
        var message = ""

        remote.doIfSuccess { message = it.message }
        remote.doIfFailure { message = it.localizedMessage ?: it.message ?: "Erreur lors de la mise à jour distante" }

        return RemoteMessageBusiness(message = message)
    }

    override suspend fun deleteRemoteControlGRV(id: Int, baseUrl: String): RemoteMessageBusiness {
        TODO("Not yet implemented")
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