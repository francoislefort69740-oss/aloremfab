package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.ActiveIdLocal

@Dao
interface ActiveIdDao {

    // CREATE

    @Insert
    fun insertId(vararg userLocals: ActiveIdLocal)

    // READ

    @Query("SELECT * FROM activeIdInternalData WHERE activeId = :id LIMIT 1")
    suspend fun  findActiveId(id: Int): ActiveIdLocal

    // UPDATE

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(activeIdLocal: ActiveIdLocal)

}