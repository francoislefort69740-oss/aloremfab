package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVStep5Local

@Dao
interface ControlGRVStep5Dao {
    // CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertControlGRVStep5(controlGRVStep5Local: ControlGRVStep5Local)


    // READ

    @Query("SELECT * FROM controlGRVStep5InternalData WHERE GRVStep5Reference = :reference")
    suspend fun findControlGRVStep5ByReference(reference: Int): ControlGRVStep5Local

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep5InternalData WHERE GRVStep5Reference = :controlGRVStep5Id)")
    suspend fun controlGRVStep5IdExist(controlGRVStep5Id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep5InternalData WHERE GRV_control_uid = :controlGRVStep5Id)")
    suspend fun controlGRVStep5SerialNumberExist(controlGRVStep5Id: Int): Boolean


    // UPDATE

    @Update
    suspend fun updateControlGRVStep5(controlGRVStep5Local: ControlGRVStep5Local)


    // DELETE

    @Query("DELETE FROM controlGRVStep5InternalData WHERE GRVStep5Reference = :id")
    suspend fun deleteControlGRVStep5ById(id: Int)

}