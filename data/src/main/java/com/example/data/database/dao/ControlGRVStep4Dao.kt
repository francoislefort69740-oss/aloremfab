package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVStep4Local

@Dao
interface ControlGRVStep4Dao {
    // CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertControlGRVStep4(controlGRVStep4Local: ControlGRVStep4Local)


    // READ

    @Query("SELECT * FROM controlGRVStep4InternalData WHERE GRVStep4Reference = :reference")
    suspend fun findControlGRVStep4ByReference(reference: Int): ControlGRVStep4Local

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep4InternalData WHERE GRVStep4Reference = :controlGRVStep4Id)")
    suspend fun controlGRVStep4IdExist(controlGRVStep4Id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep4InternalData WHERE GRV_control_uid = :controlGRVStep4Id)")
    suspend fun controlGRVStep4SerialNumberExist(controlGRVStep4Id: Int): Boolean


    // UPDATE

    @Update
    suspend fun updateControlGRVStep4(controlGRVStep4Local: ControlGRVStep4Local)


    // DELETE

    @Query("DELETE FROM controlGRVStep4InternalData WHERE GRVStep4Reference = :id")
    suspend fun deleteControlGRVStep4ById(id: Int)

}