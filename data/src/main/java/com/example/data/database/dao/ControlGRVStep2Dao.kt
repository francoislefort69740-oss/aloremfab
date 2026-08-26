package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ControlGRVStep2Local


@Dao
interface ControlGRVStep2Dao {
    // CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertControlGRVStep2(controlGRVStep2Local: ControlGRVStep2Local)

    // READ

    @Query("SELECT * FROM controlGRVStep2InternalData WHERE GRVStep2Reference = :reference")
    fun findControlGRVStep2ByReference(reference: Int): ControlGRVStep2Local

    @Query("SELECT EXISTS(SELECT * FROM controlGRVStep2InternalData WHERE GRVStep2Reference = :controlGRVStep2Id)")
    fun controlGRVStep2IdExist(controlGRVStep2Id: Int): Boolean

    // UPDATE

    @Update (onConflict = OnConflictStrategy.REPLACE)
    fun update(controlGRVStep2Local: ControlGRVStep2Local)

    // DELETE

    @Query("DELETE FROM controlGRVStep2InternalData WHERE GRVStep2Reference = :id")
    fun deleteControlGRVStep2ById(id: Int)

}