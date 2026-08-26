package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVStep6Local

@Dao
interface ControlGRVStep6Dao {
    // CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertControlGRVStep6(controlGRVStep6Local: ControlGRVStep6Local)


    // READ

    @Query("SELECT * FROM controlGRVStep6InternalData WHERE GRVStep6Reference = :reference")
    suspend fun findControlGRVStep6ByReference(reference: Int): ControlGRVStep6Local

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep6InternalData WHERE GRVStep6Reference = :controlGRVStep6Id)")
    suspend fun controlGRVStep6IdExist(controlGRVStep6Id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep6InternalData WHERE GRV_control_uid = :controlGRVStep6Id)")
    suspend fun controlGRVStep6SerialNumberExist(controlGRVStep6Id: Int): Boolean


    // UPDATE

    @Update
    suspend fun updateControlGRVStep6(controlGRVStep6Local: ControlGRVStep6Local)


    // DELETE

    @Query("DELETE FROM controlGRVStep6InternalData WHERE GRVStep6Reference = :id")
    suspend fun deleteControlGRVStep6ById(id: Int)
}