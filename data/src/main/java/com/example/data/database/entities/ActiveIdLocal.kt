package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ActiveIdLocal.TABLE_NAME,
    indices = [Index(value = [ActiveIdLocal.ACTIVE_ID], unique = true)]
)
class ActiveIdLocal(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ACTIVE_ID) val uid: Int,
    @ColumnInfo(name = "id") val activeId: Int
) {
    companion object {
        const val TABLE_NAME = "activeIdInternalData"
        const val ACTIVE_ID = "activeId"
    }
}