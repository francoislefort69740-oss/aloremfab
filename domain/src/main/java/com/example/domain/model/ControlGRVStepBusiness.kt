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
        var marquePrincipale: String,
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
        var unauthorizedRepair: Boolean,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return reference != 0 && controlGRVForeignId != 0
        }
    }

    data class ControlGRVStep4(
        override val reference: Int,
        var internalNA: Boolean,
        var internalOK: Boolean?,
        var internalClean: Boolean,
        var internalObjectInside: Boolean,
        var internalPollution: Boolean,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return reference != 0 && controlGRVForeignId != 0
        }
    }

    data class ControlGRVStep5(
        override val reference: Int,
        var epaisseurNA: Boolean,
        var epaisseurMinSideFront: Int,
        var epaisseurMinSideBack: Int,
        var epaisseurMinSideRight: Int,
        var epaisseurMinSideLeft: Int,
        var epaisseurSideFrontResult1: Int,
        var epaisseurSideFrontResult2: Int,
        var epaisseurSideFrontResult3: Int,
        var epaisseurSideFrontResult4: Int,
        var epaisseurSideFrontResult5: Int,
        var epaisseurSideBackResult1: Int,
        var epaisseurSideBackResult2: Int,
        var epaisseurSideBackResult3: Int,
        var epaisseurSideBackResult4: Int,
        var epaisseurSideBackResult5: Int,
        var epaisseurSideRightResult1: Int,
        var epaisseurSideRightResult2: Int,
        var epaisseurSideRightResult3: Int,
        var epaisseurSideRightResult4: Int,
        var epaisseurSideRightResult5: Int,
        var epaisseurSideLeftResult1: Int,
        var epaisseurSideLeftResult2: Int,
        var epaisseurSideLeftResult3: Int,
        var epaisseurSideLeftResult4: Int,
        var epaisseurSideLeftResult5: Int,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return reference != 0 && controlGRVForeignId != 0
        }
    }

    data class ControlGRVStep6(
        override val reference: Int,
        var etancheiteOK1: Boolean?,
        var etancheiteDate1: String,
        var etancheiteBar1: Float,
        var etancheiteOK2: Boolean?,
        var etancheiteDate2: String,
        var etancheiteBar2: Float,
        var controlGRVForeignId: Int
    ) : ControlGRVStepBusiness() {
        override fun isValid(): Boolean {
            return reference != 0 && controlGRVForeignId != 0
        }
    }


    abstract val reference: Int

    abstract fun isValid(): Boolean

}
