package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = UserLocal.TABLE_NAME,
    indices = [Index(value = [UserLocal.FIRST_NAME], unique = true)]
)
class UserLocal(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = LOCAL_USER_ID) val uid: Int,
    @ColumnInfo(name = FIRST_NAME) val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String
) {
    companion object {
        const val TABLE_NAME = "userInternalData"
        const val FIRST_NAME = "first_name"
        const val LOCAL_USER_ID = "LOCAL_USER_ID"
    }
}