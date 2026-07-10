package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.UserLocal

@Dao
interface UserDao {

    // CREATE

    @Insert
    fun insertUser(vararg userLocals: UserLocal)

    // READ

    @Query("SELECT * FROM userInternalData ORDER BY first_name")
    fun getAll(): List<UserLocal>

    @Query("SELECT * FROM  userInternalData WHERE LOCAL_USER_ID = :id LIMIT 1")
    suspend fun findUserDataById(id: Int): UserLocal

    @Query("SELECT EXISTS(SELECT * FROM userInternalData WHERE LOCAL_USER_ID = :userId)")
    suspend fun userIdExist(userId: Int): Boolean

    @Query("SELECT * FROM userInternalData WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findUserByName(first: String, last: String): UserLocal

    // UPDATE

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(userLocal: UserLocal)

    // DELETE

    @Query("DELETE FROM userInternalData WHERE first_name LIKE :first AND " + "last_name LIKE :last")
    suspend fun deleteUserDataByName(first: String, last: String)

    @Query("DELETE FROM userInternalData")
    suspend fun deleteAll()
}