package com.example.data.repository

import com.example.data.database.repository.ActiveIdLocalDataSource
import com.example.domain.model.ActiveIdBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository

class ActiveIdRepositoryImpl(private val activeIdLocalDataSource: ActiveIdLocalDataSource): ActiveIdLocalRepository {
    override suspend fun getActiveId(uiD: Int): ActiveIdBusiness {
        return activeIdLocalDataSource.getActiveIdLocal(uiD = uiD)
    }

    override suspend fun updateActiveId(activeIdBusiness: ActiveIdBusiness): Int {
        activeIdLocalDataSource.updateLocalActiveId(activeIdBusiness = activeIdBusiness)
        return activeIdBusiness.activeId
    }
}