package com.example.domain.model

sealed class ControlGRVStepBusiness{

    data class ControlGRVStep0(
        override val reference: Int,
        var reportNumber: Int,
        var customer: String,
        var customerSerialNumber: Int,
        var serialNumberAlorem: Int,
        var type: String,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return  reference != 0 &&
                    reportNumber != 0 &&
                    customer != "" &&
                    customerSerialNumber != 0 &&
                    serialNumberAlorem != 0 &&
                    type != "" &&
                    controlGRVForeignId != 0
        }
    }

    data class ControlGRVStep1(
        override val reference: Int,
        var fabricationPlateAdr: Boolean?,
        var aloremPlate: Boolean?,
        var bookletPouch: Boolean?,
        var userManual: Boolean?,
        var instructionOfUse: Boolean?,
        var certificatesADR: Boolean?,
        var groundingAdhesive: Boolean?,
        var conformityCertificateMarking: Boolean?,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return  reference != 0 && controlGRVForeignId != 0
        }
    }

    data class ControlGRVStep2(
        override val reference: Int,
        var tare: Int,
        var material: String,
        var capacity20: Int,
        var grossMass: Int,
        var fabricationDate: String,
        var shellThickness: Int,
        var pictogramStacking: Boolean?,
        var weightStacking: Int,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return  reference != 0 && controlGRVForeignId != 0
        }
    }

    data class ControlGRVStep3(
        override val reference: Int,
        var bottomRetentionFace: Int?,
        var bottomRetentionRight: Int?,
        var bottomRetentionLeft: Int?,
        var bottomRetentionBehind: Int?,
        var upperRetention: Int?,
        var liftingRings: Int?,
        var forkliftPass: Int?,
        var dashboard: Int?,
        var unauthorizedRepair: Boolean?,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return reference != 0 && controlGRVForeignId != 0
        }

    }


    abstract val reference: Int

    abstract fun isValid(): Boolean

}
