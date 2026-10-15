package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class SendControlGRVToRemoteUseCase(
    private val controlGRVLocalRepository: ControlGRVLocalRepository,
    private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository,
) {
    suspend operator fun invoke(reference: Int, list: List<ControlGRVBusiness>, baseUrl: String): ResultOf<String> {
        return try {
            val allSteps = ControlGRVStepBusiness.ControlGRVAllStep()

            allSteps.controlGRVStep0 = runCatching {
                controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep0::class
                ) as? ControlGRVStepBusiness.ControlGRVStep0
            }.getOrNull()

            allSteps.controlGRVStep1 = runCatching {
                controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep1::class
                ) as? ControlGRVStepBusiness.ControlGRVStep1
            }.getOrNull()

            allSteps.controlGRVStep2 = runCatching {
                controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep2::class
                ) as? ControlGRVStepBusiness.ControlGRVStep2
            }.getOrNull()

            allSteps.controlGRVStep3 = runCatching {
                controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep3::class
                ) as? ControlGRVStepBusiness.ControlGRVStep3
            }.getOrNull()

            allSteps.controlGRVStep4 = runCatching {
                controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep4::class
                ) as? ControlGRVStepBusiness.ControlGRVStep4
            }.getOrNull()

            allSteps.controlGRVStep5 = runCatching {
                controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep5::class
                ) as? ControlGRVStepBusiness.ControlGRVStep5
            }.getOrNull()

            allSteps.controlGRVStep6 = runCatching {
                controlGRVStepLocalRepository.getLocalControlGRVStepByReference(
                    reference = reference,
                    type = ControlGRVStepBusiness.ControlGRVStep6::class
                ) as? ControlGRVStepBusiness.ControlGRVStep6
            }.getOrNull()

            val controlGRV = controlGRVLocalRepository.getLocalControlGRVBySerialNumber(serialNumber = reference)

            // Recherche de la commande existante dans la liste distante
            val remoteControl = list.find { it.serialNumber == reference }

            val responseMessage = if (remoteControl != null) {
                // Utilise l'ID (uid) de la commande sur le serveur distant si disponible
                val updateId = remoteControl.uid ?: reference
                controlGRV.uid = remoteControl.uid ?: controlGRV.uid

                controlGRVLocalRepository.updateRemoteControlGRV(
                    id = updateId,
                    controlGRVBusiness = controlGRV,
                    allSteps = allSteps,
                    baseUrl = baseUrl
                ).message
            } else {
                controlGRVLocalRepository.createRemoteControlGRV(
                    controlGRVBusiness = controlGRV,
                    allSteps = allSteps,
                    baseUrl = baseUrl
                ).message
            }

            ResultOf.Success(responseMessage)
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}
