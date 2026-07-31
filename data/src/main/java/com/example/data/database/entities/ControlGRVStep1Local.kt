package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.database.entities.ControlGRVStep1Local.Companion.GRV_CONTROL_UID
import com.example.data.database.entities.ControlGRVStep1Local.Companion.REFERENCE
import com.example.data.database.entities.ControlGRVStep1Local.Companion.TABLE_NAME


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
data class ControlGRVStep1Local(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = REFERENCE) val reference: Int,
    @ColumnInfo(name = GRV_CONTROL_UID, index = true) val foreignKey: Int,
    @ColumnInfo(name = FABRICATION_PLATE_ADR) val fabricationPlateAdr: Boolean?,
    @ColumnInfo(name = ALOREM_PLATE) val aloremPlate: Boolean?,
    @ColumnInfo(name = BOOKLET_POUCH) val bookletPouch: Boolean?,
    @ColumnInfo(name = USER_MANUAL) val userManual: Boolean?,
    @ColumnInfo(name = INSTRUCTION_OF_USE) val instructionOfUse: Boolean?,
    @ColumnInfo(name = CERTIFICATES_ADR) val certificatesADR: Boolean?,
    @ColumnInfo(name = GROUNDING_ADHESIVE) val groundingAdhesive: Boolean?,
    @ColumnInfo(name = CONFORMITY_CERTIFICATE_MARKING) val conformityCertificateMarking: Boolean?
) {

    companion object {
        const val TABLE_NAME = "controlGRVStep1InternalData"
        const val REFERENCE = "GRVStep1Reference"
        const val FABRICATION_PLATE_ADR = "fabrication_plate_adr"
        const val ALOREM_PLATE = "alorem_plate"
        const val BOOKLET_POUCH = "booklet_pouch"
        const val USER_MANUAL = "user_manual"
        const val INSTRUCTION_OF_USE = "instruction_of_use"
        const val CERTIFICATES_ADR = "certificates_adr"
        const val GROUNDING_ADHESIVE = "grounding_adhesive"
        const val CONFORMITY_CERTIFICATE_MARKING = "conformity_certificate_marking"
        const val GRV_CONTROL_UID = "GRV_control_uid"
    }

}