package com.example.domain.repository.db

import com.example.domain.model.ActiveIdBusiness

interface ActiveIdLocalRepository {
    // READ
    suspend fun getActiveId(uiD: Int): ActiveIdBusiness

    // UPDATE
    suspend fun updateActiveId(activeIdBusiness: ActiveIdBusiness): Int
}