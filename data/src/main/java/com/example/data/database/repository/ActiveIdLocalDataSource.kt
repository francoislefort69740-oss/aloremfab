package com.example.data.database.repository

import com.example.domain.model.ActiveIdBusiness

interface ActiveIdLocalDataSource {
    // CREATE
    suspend fun createLocalActiveId(activeIdBusiness: ActiveIdBusiness)

    // READ
    suspend fun getActiveIdLocal(uiD: Int): ActiveIdBusiness

    // UPDATE
    suspend fun updateLocalActiveId(activeIdBusiness: ActiveIdBusiness)
}