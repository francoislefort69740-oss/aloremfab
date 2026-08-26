package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVStep1Local

@Dao
interface ControlGRVStep1Dao {
    // CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertControlGRVStep1(controlGRVStep1Local: ControlGRVStep1Local)


    // READ

    @Query("SELECT * FROM controlGRVStep1InternalData WHERE GRVStep1Reference = :reference")
    suspend fun findControlGRVStep1ByReference(reference: Int): ControlGRVStep1Local

    @Query("SELECT * FROM controlGRVStep1InternalData WHERE GRV_control_uid = :serialNumber")
    suspend fun findControlGRVStep1BySerialNumber(serialNumber: Int): ControlGRVStep1Local

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep1InternalData WHERE GRVStep1Reference = :controlGRVStep1Id)")
    suspend fun controlGRVStep1IdExist(controlGRVStep1Id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep1InternalData WHERE GRV_control_uid = :controlGRVStep1Id)")
    suspend fun controlGRVStep1SerialNumberExist(controlGRVStep1Id: Int): Boolean


    // UPDATE

    @Update
    suspend fun updateControlGRVStep1(controlGRVStep1Local: ControlGRVStep1Local)


    // DELETE

    @Query("DELETE FROM controlGRVStep1InternalData WHERE GRVStep1Reference = :id")
    suspend fun deleteControlGRVStep1ById(id: Int)

}