package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVStep3Local

@Dao
interface ControlGRVStep3Dao {
    // CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertControlGRVStep3(controlGRVStep3Local: ControlGRVStep3Local)

    // READ

    @Query("SELECT * FROM controlGRVStep3InternalData WHERE GRVStep3Reference = :reference")
    fun getControlGRVStep3ByReference(reference: Int): ControlGRVStep3Local

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep3InternalData WHERE GRVStep3Reference = :reference)")
    fun controlGRVStep3Exists(reference: Int): Boolean

    // UPDATE

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(controlGRVStep3Local: ControlGRVStep3Local)

    // DELETE
    @Query("DELETE FROM controlGRVStep3InternalData WHERE GRVStep3Reference = :id")
    fun deleteControlGRVStep3ById(id: Int)

}