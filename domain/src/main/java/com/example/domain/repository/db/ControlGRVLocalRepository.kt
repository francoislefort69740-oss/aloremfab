package com.example.domain.repository.db

import com.example.domain.model.ControlGRVBusiness

interface ControlGRVLocalRepository {
    // CREATE
    suspend fun createLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int

    // READ
    suspend fun getLocalControlGRVById(id: Int): ControlGRVBusiness
    suspend fun getLocalControlGRVBySerialNumber(serialNumber: Int): ControlGRVBusiness
    suspend fun checkIfControlGRVExist(controlGRVId: Int): Boolean
    suspend fun getCurrentlyGoingOn(): List<ControlGRVBusiness>
    suspend fun getAllControlGRV(): List<ControlGRVBusiness>
    suspend fun getLoaded(): List<ControlGRVBusiness>
    suspend fun getUnloaded(): List<ControlGRVBusiness>


    // UPDATE
    suspend fun updateLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int

    // DELETE
    suspend fun deleteControlGRV(id: Int): Boolean
    suspend fun deleteAllControlGRV()

}