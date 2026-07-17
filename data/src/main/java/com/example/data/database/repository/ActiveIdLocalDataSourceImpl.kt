package com.example.data.database.repository

import com.example.data.database.dao.ActiveIdDao
import com.example.data.mapper.UserMapper
import com.example.domain.model.ActiveIdBusiness

open class ActiveIdLocalDataSourceImpl(private val activeIdDao: ActiveIdDao): ActiveIdLocalDataSource {
    override suspend fun createLocalActiveId(activeIdBusiness: ActiveIdBusiness) {
        activeIdDao.insertId(UserMapper.activeIdBusinessToLocal(activeIdBusiness = activeIdBusiness))
    }

    override suspend fun getActiveIdLocal(uid: Int): ActiveIdBusiness {
        return UserMapper.activeIdLocalToBusiness(activeIdLocal = activeIdDao.findActiveId(uid))
    }

    override suspend fun updateLocalActiveId(activeIdBusiness: ActiveIdBusiness) {
        activeIdDao.update(UserMapper.activeIdBusinessToLocal(activeIdBusiness = activeIdBusiness))
    }
}