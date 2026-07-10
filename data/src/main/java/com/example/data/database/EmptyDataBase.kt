package com.example.data.database

import com.example.data.database.dao.UserDao
import com.example.data.database.entities.UserLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun rePopulateDb(database: MyDatabase?){
    database?.let { db ->
        withContext(Dispatchers.IO) {
            val userDataDao: UserDao = db.userDao()

            userDataDao.deleteAll()

            val userLocal = UserLocal(0, firstName = "DOE", lastName = "John", email = "no email")

            userDataDao.insertUser(userLocal)
        }
    }
}
