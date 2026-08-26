package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.database.entities.ControlGRVStep6Local.Companion.GRV_CONTROL_UID
import com.example.data.database.entities.ControlGRVStep6Local.Companion.REFERENCE
import com.example.data.database.entities.ControlGRVStep6Local.Companion.TABLE_NAME

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
data class ControlGRVStep6Local(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = REFERENCE) val reference: Int,
    @ColumnInfo(name = GRV_CONTROL_UID, index = true) val foreignKey: Int,
    @ColumnInfo(name = ETANCHEITE_OK_1) val etancheiteOK1: Boolean?,
    @ColumnInfo(name = ETANCHEITE_DATE_1) val etancheiteDate1: String,
    @ColumnInfo(name = ETANCHEITE_BAR_1) val etancheiteBar1: Float,
    @ColumnInfo(name = ETANCHEITE_OK_2) val etancheiteOK2: Boolean?,
    @ColumnInfo(name = ETANCHEITE_DATE_2) val etancheiteDate2: String,
    @ColumnInfo(name = ETANCHEITE_BAR_2) val etancheiteBar2: Float
){

    companion object {
        const val TABLE_NAME = "controlGRVStep6InternalData"
        const val REFERENCE = "GRVStep6Reference"
        const val ETANCHEITE_OK_1 = "etancheite_ok_1"
        const val ETANCHEITE_DATE_1 = "etancheite_date_1"
        const val ETANCHEITE_BAR_1 = "etancheite_bar_1"
        const val ETANCHEITE_OK_2 = "etancheite_ok_2"
        const val ETANCHEITE_DATE_2 = "etancheite_date_2"
        const val ETANCHEITE_BAR_2 = "etancheite_bar_2"
        const val GRV_CONTROL_UID = "GRV_control_uid"
    }

}
