package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVLocal

@Dao
interface ControlGRVDao {
    // CREATE

    @Insert
    fun insertControlGRV(vararg controlGRVLocals: ControlGRVLocal)


    // READ

    @Query("SELECT * FROM controlGRVInternalData ORDER BY serial_number")
    fun getAll(): List<ControlGRVLocal>

    @Query("SELECT * FROM  controlGRVInternalData WHERE uid = :id LIMIT 1")
    suspend fun findControlGRVById(id: Int): ControlGRVLocal

    @Query("SELECT COUNT(*) FROM controlGRVInternalData WHERE uid = :controlGRVId")
    suspend fun controlGRVIdExist(controlGRVId: Int): Boolean

    @Query("SELECT * FROM controlGRVInternalData WHERE serial_number LIKE :serialNumber LIMIT 1")
    fun findControlGRVBySerialNumber(serialNumber: Int): ControlGRVLocal

    @Query(" SELECT * FROM controlGRVInternalData WHERE currently_going_on = 1 ORDER BY serial_number")
    suspend fun getCurrentlyGoingOn(): List<ControlGRVLocal>

    @Query(" SELECT * FROM controlGRVInternalData WHERE loaded = 1 ORDER BY serial_number")
    suspend fun getLoaded(): List<ControlGRVLocal>

    @Query(" SELECT * FROM controlGRVInternalData WHERE loaded = 0 ORDER BY serial_number")
    suspend fun getUnLoaded(): List<ControlGRVLocal>


    // UPDATE

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(controlGRVLocal: ControlGRVLocal)


    // DELETE

    @Query("DELETE FROM controlGRVInternalData WHERE uid = :id")
    suspend fun deleteControlGRVById(id: Int)

    @Query("DELETE FROM controlGRVInternalData")
    suspend fun deleteAll()


}