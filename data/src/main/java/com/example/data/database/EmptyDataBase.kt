package com.example.data.database

import com.example.data.database.dao.ActiveIdDao
import com.example.data.database.dao.UserDao
import com.example.data.database.entities.ActiveIdLocal
import com.example.data.database.entities.UserLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun rePopulateDb(database: MyDatabase?){
    database?.let { db ->
        withContext(Dispatchers.IO) {
            val userDataDao: UserDao = db.userDao()
            val activeIdDao: ActiveIdDao = db.activeIdDao()

            userDataDao.deleteAll()

            val userLocal = UserLocal(0, firstName = "DOE", lastName = "John", email = "no email")
            val activeIdLocal = ActiveIdLocal(uid = 0, activeId = 0)

            userDataDao.insertUser(userLocal)
            activeIdDao.insertId(activeIdLocal)
        }
    }
}
