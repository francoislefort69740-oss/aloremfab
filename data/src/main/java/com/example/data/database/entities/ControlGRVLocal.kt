package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ControlGRVLocal.TABLE_NAME,
    indices = [Index(value = [ControlGRVLocal.UID], unique = true)]
)
class ControlGRVLocal(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = UID) val uid: Int,
    @ColumnInfo(name = SERIAL_NUMBER) val serialNumber: Int,
    @ColumnInfo(name = CURRENT_STEP) val currentStep: Int,
    @ColumnInfo(name = CURRENTLY_GOING_ON) val currentlyGoingOn: Boolean
) {

    companion object {
        const val TABLE_NAME = "controlGRVInternalData"
        const val UID = "uid"
        const val SERIAL_NUMBER = "serial_number"
        const val CURRENT_STEP = "current_step"
        const val CURRENTLY_GOING_ON = "currently_going_on"
    }
}