package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.database.entities.ControlGRVStep3Local.Companion.GRV_CONTROL_UID
import com.example.data.database.entities.ControlGRVStep3Local.Companion.REFERENCE
import com.example.data.database.entities.ControlGRVStep3Local.Companion.TABLE_NAME


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
data class ControlGRVStep3Local(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = REFERENCE) val reference: Int,
    @ColumnInfo(name = BOTTOM_RETENTION_FACE) val bottomRetentionFace: Int?,
    @ColumnInfo(name = BOTTOM_RETENTION_RIGHT) val bottomRetentionRight: Int?,
    @ColumnInfo(name = BOTTOM_RETENTION_LEFT) val bottomRetentionLeft: Int?,
    @ColumnInfo(name = BOTTOM_RETENTION_BEHIND) val bottomRetentionBehind: Int?,
    @ColumnInfo(name = UPPER_RETENTION) val upperRetention: Int?,
    @ColumnInfo(name = LIFTING_RINGS) val liftingRings: Int?,
    @ColumnInfo(name = FORKLIFT_PASS) val forkliftPass: Int?,
    @ColumnInfo(name = DASHBOARD) val dashboard: Int?,
    @ColumnInfo(name = UNAUTHORIZED_REPAIR) val unauthorizedRepair: Boolean,
    @ColumnInfo(name = GRV_CONTROL_UID, index = true) val foreignKey: Int
) {
    companion object {
        const val TABLE_NAME = "controlGRVStep3InternalData"
        const val REFERENCE = "GRVStep3Reference"
        const val BOTTOM_RETENTION_FACE = "bottom_retention_face"
        const val BOTTOM_RETENTION_RIGHT = "bottom_retention_right"
        const val BOTTOM_RETENTION_LEFT = "bottom_retention_left"
        const val BOTTOM_RETENTION_BEHIND = "bottom_retention_behind"
        const val UPPER_RETENTION = "upper_retention"
        const val LIFTING_RINGS = "lifting_rings"
        const val FORKLIFT_PASS = "forklift_pass"
        const val DASHBOARD = "dashboard"
        const val UNAUTHORIZED_REPAIR = "unauthorized_repair"
        const val GRV_CONTROL_UID = "GRV_control_uid"
    }
}
