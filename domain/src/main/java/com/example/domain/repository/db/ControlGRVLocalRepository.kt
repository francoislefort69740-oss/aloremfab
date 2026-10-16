package com.example.domain.repository.db

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.RemoteMessageBusiness

interface ControlGRVLocalRepository {
    // CREATE
    suspend fun createLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int

    // READ
    suspend fun getLocalControlGRVById(id: Int): ControlGRVBusiness
    suspend fun getLocalControlGRVBySerialNumber(serialNumber: Int): ControlGRVBusiness
    suspend fun checkIfControlGRVExist(controlGRVId: Int): Boolean
    suspend fun getCurrentlyGoingOn(): List<ControlGRVBusiness>
    suspend fun getAllControlGRV(): List<ControlGRVBusiness>
    suspend fun getAllRemoteAndLocalControlGRV(baseUrl: String): Pair<List<ControlGRVBusiness>, List<ControlGRVBusiness>>
    suspend fun getLoaded(): List<ControlGRVBusiness>
    suspend fun getUnloaded(): List<ControlGRVBusiness>
    suspend fun createRemoteControlGRV(controlGRVBusiness: ControlGRVBusiness, baseUrl: String, allSteps: ControlGRVStepBusiness.ControlGRVAllStep): RemoteMessageBusiness
    suspend fun updateRemoteControlGRV(id: Int, controlGRVBusiness: ControlGRVBusiness, baseUrl: String, allSteps: ControlGRVStepBusiness.ControlGRVAllStep): RemoteMessageBusiness
    suspend fun deleteRemoteControlGRV(id: Int, baseUrl: String): RemoteMessageBusiness
    suspend fun getRemoteControlById(id: Int, baseUrl: String): ResultOf<Pair<ControlGRVBusiness, ControlGRVStepBusiness.ControlGRVAllStep>>

    // UPDATE
    suspend fun updateLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int

    // DELETE
    suspend fun deleteControlGRV(id: Int): Boolean
    suspend fun deleteAllControlGRV()
}
