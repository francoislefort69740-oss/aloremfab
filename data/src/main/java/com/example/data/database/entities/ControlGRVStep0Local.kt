package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.database.entities.ControlGRVStep0Local.Companion.GRV_CONTROL_UID
import com.example.data.database.entities.ControlGRVStep0Local.Companion.REFERENCE
import com.example.data.database.entities.ControlGRVStep0Local.Companion.TABLE_NAME

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
data class ControlGRVStep0Local(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = REFERENCE) val reference: Int,
    @ColumnInfo(name = REPORT_NUMBER) val reportNumber: Int,
    @ColumnInfo(name = CUSTOMER) val customer: String,
    @ColumnInfo(name = CUSTOMER_SERIAL_NUMBER) val customerSerialNumber: Int,
    @ColumnInfo(name = SERIAL_NUMBER_ALOREM) val serialNumberAlorem: Int,
    @ColumnInfo(name = TYPE) val type: String,
    @ColumnInfo(name = GRV_CONTROL_UID, index = true) val foreignKey: Int
) {

    companion object {
        const val TABLE_NAME = "controlGRVStep0InternalData"
        const val REFERENCE = "GRVStepReference"
        const val REPORT_NUMBER = "report_number"
        const val CUSTOMER = "customer"
        const val CUSTOMER_SERIAL_NUMBER = "customer_serial_number"
        const val SERIAL_NUMBER_ALOREM = "serial_number_alorem"
        const val TYPE = "GRV_type"
        const val GRV_CONTROL_UID = "GRV_control_uid"
    }

}