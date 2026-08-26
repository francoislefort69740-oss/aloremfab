package com.example.data.database

import com.example.data.database.dao.ActiveIdDao
import com.example.data.database.dao.UserDao
import com.example.data.database.entities.ActiveIdLocal
import com.example.data.database.entities.ControlGRVLocal
import com.example.data.database.entities.UserLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun rePopulateDb(database: MyDatabase?){
    database?.let { db ->
        withContext(Dispatchers.IO) {
            val userDataDao: UserDao = db.userDao()
            val activeIdDao: ActiveIdDao = db.activeIdDao()
            val controlGRVDao = db.controlGRVDao()

            userDataDao.deleteAll()
            controlGRVDao.deleteAll()

            val activeIdLocal = ActiveIdLocal(uid = 0, activeId = 1)
            activeIdDao.insertId(activeIdLocal)
        }
    }
}
