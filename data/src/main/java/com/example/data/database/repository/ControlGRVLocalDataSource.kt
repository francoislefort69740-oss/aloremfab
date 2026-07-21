package com.example.data.database.repository

import com.example.domain.model.ControlGRVBusiness

interface ControlGRVLocalDataSource {
    // CREATE
    suspend fun createLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int

    // READ
    suspend fun getLocalControlGRVById(id: Int): ControlGRVBusiness
    suspend fun getLocalControlGRVBySerialNumber(serialNumber: Int): ControlGRVBusiness
    suspend fun checkIfControlGRVExist(controlGRVId: Int): Boolean
    suspend fun getAllControlGRV(): List<ControlGRVBusiness>
    suspend fun getCurrentlyGoingOn(): List<ControlGRVBusiness>

    // UPDATE
    suspend fun updateLocalControlGRV(controlGRVBusiness: ControlGRVBusiness): Int

    // DELETE
    suspend fun deleteControlGRV(id: Int): Boolean
    suspend fun deleteAllControlGRV()
}