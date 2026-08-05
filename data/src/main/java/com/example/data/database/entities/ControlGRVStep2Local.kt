package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.database.entities.ControlGRVStep2Local.Companion.GRV_CONTROL_UID
import com.example.data.database.entities.ControlGRVStep2Local.Companion.REFERENCE
import com.example.data.database.entities.ControlGRVStep2Local.Companion.TABLE_NAME

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
data class ControlGRVStep2Local(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = REFERENCE) val reference: Int,
    @ColumnInfo(name = TARE) val tare: Int,
    @ColumnInfo(name = MATERIAL) val material: String,
    @ColumnInfo(name = CAPACITY20) val capacity20: Int,
    @ColumnInfo(name = GROSS_MASS) val grossMass: Int,
    @ColumnInfo(name = FABRICATION_DATE) val fabricationDate: String,
    @ColumnInfo(name = SHELL_THICKNESS) val shellThickness: Int,
    @ColumnInfo(name = PICTOGRAM_STACKING) val pictogramStacking: Boolean?,
    @ColumnInfo(name = WEIGHT_STACKING) val weightStacking: Int,
    @ColumnInfo(name = GRV_CONTROL_UID, index = true) val foreignKey: Int
){
    companion object {
        const val TABLE_NAME = "controlGRVStep2InternalData"
        const val REFERENCE = "GRVStepReference"
        const val TARE = "tare"
        const val MATERIAL = "material"
        const val CAPACITY20 = "capacity20"
        const val GROSS_MASS = "gross_mass"
        const val FABRICATION_DATE = "fabrication_date"
        const val SHELL_THICKNESS = "shell_thickness"
        const val PICTOGRAM_STACKING = "pictogram_stacking"
        const val WEIGHT_STACKING = "weight_stacking"
        const val GRV_CONTROL_UID = "GRV_control_uid"
    }
}
