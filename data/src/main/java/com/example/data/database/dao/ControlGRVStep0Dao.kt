package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVStep0Local

@Dao
interface ControlGRVStep0Dao {
    // CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertControlGRVStep0(controlGRVStep0Local: ControlGRVStep0Local)

    // READ
    @Query("SELECT * FROM controlGRVStep0InternalData WHERE GRVStepReference = :reference")
    fun findControlGRVStep0ByReference(reference: Int): ControlGRVStep0Local

    @Query("SELECT * FROM controlGRVStep0InternalData WHERE serial_number_alorem = :serialNumber")
    fun findControlGRVStep0BySerialNumber(serialNumber: Int): ControlGRVStep0Local

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep0InternalData WHERE GRVStepReference = :controlGRVStep0Id)")
    fun controlGRVStep0IdExist(controlGRVStep0Id: Int): Boolean

    // UPDATE
    @Update (onConflict = OnConflictStrategy.REPLACE)
    fun update(controlGRVStep0Local: ControlGRVStep0Local)


    // DELETE
    @Query("DELETE FROM controlGRVStep0InternalData WHERE GRVStepReference = :id")
    fun deleteControlGRVStep0ById(id: Int)

}