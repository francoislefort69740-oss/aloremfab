package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.database.entities.ControlGRVStep4Local.Companion.GRV_CONTROL_UID
import com.example.data.database.entities.ControlGRVStep4Local.Companion.REFERENCE
import com.example.data.database.entities.ControlGRVStep4Local.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ControlGRVLocal::class,
            parentColumns = [ControlGRVLocal.UID],
            childColumns = [GRV_CONTROL_UID],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = [REFERENCE], unique = true)]
)
data class ControlGRVStep4Local(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = REFERENCE) val reference: Int,
    @ColumnInfo(name = GRV_CONTROL_UID, index = true) val foreignKey: Int,
    @ColumnInfo(name = INTERNAL_NA) val internalNA: Boolean,
    @ColumnInfo(name = INTERNAL_OK) val internalOK: Boolean?,
    @ColumnInfo(name = INTERNAL_CLEAN) val internalClean: Boolean,
    @ColumnInfo(name = INTERNAL_OBJECT_INSIDE) val internalObjectInside: Boolean,
    @ColumnInfo(name = INTERNAL_POLLUTION) val internalPollution: Boolean
) {

    companion object {
        const val TABLE_NAME = "controlGRVStep4InternalData"
        const val REFERENCE = "GRVStep4Reference"
        const val INTERNAL_NA = "internal_na"
        const val INTERNAL_OK = "internal_ok"
        const val INTERNAL_CLEAN = "internal_clean"
        const val INTERNAL_OBJECT_INSIDE = "internal_object_inside"
        const val INTERNAL_POLLUTION = "internal_pollution"
        const val GRV_CONTROL_UID = "GRV_control_uid"
    }

}
