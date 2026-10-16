package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository

class GetRemoteControlByIdUseCase(
    private val controlGRVLocalRepository: ControlGRVLocalRepository,
    private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository
) {
    suspend operator fun invoke(id: Int, baseUrl: String): ResultOf<String> {
        return try {
            // 1. Interroger la liste globale des commandes distantes (Postgres)
            val remoteAndLocal = controlGRVLocalRepository.getAllRemoteAndLocalControlGRV(baseUrl = baseUrl)
            val remoteList = remoteAndLocal.first

            // 2. Trouver la commande distante correspondante en cherchant par UID ou par SerialNumber
            val remoteItem = remoteList.find { it.uid == id } ?: remoteList.find { it.serialNumber == id }

            val targetUid = remoteItem?.uid ?: id
            val targetSerialNumber = remoteItem?.serialNumber ?: id

            // 3. Récupérer la commande et ses étapes (avec tentative par UID puis fallback par SerialNumber)
            var result: ResultOf<Pair<ControlGRVBusiness, ControlGRVStepBusiness.ControlGRVAllStep>> =
                controlGRVLocalRepository.getRemoteControlById(id = targetUid, baseUrl = baseUrl)

            if (result is ResultOf.Error && targetUid != targetSerialNumber) {
                val fallback = controlGRVLocalRepository.getRemoteControlById(id = targetSerialNumber, baseUrl = baseUrl)
                if (fallback is ResultOf.Success) {
                    result = fallback
                }
            }

            when (result) {
                is ResultOf.Success -> {
                    val (control, allSteps) = result.data

                    // 4. Remplacer l'UID par le serialNumber pour la base de données locale Room
                    control.uid = targetSerialNumber

                    // 5. Sauvegarder ou mettre à jour la commande principale dans Room
                    if (controlGRVLocalRepository.checkIfControlGRVExist(targetSerialNumber)) {
                        controlGRVLocalRepository.updateLocalControlGRV(control)
                    } else {
                        controlGRVLocalRepository.createLocalControlGRV(control)
                    }

                    // 6. Enregistrer toutes les étapes dans Room en utilisant targetSerialNumber comme clé étrangère
                    suspend fun saveStep(step: ControlGRVStepBusiness?) {
                        step?.let {
                            when (it) {
                                is ControlGRVStepBusiness.ControlGRVStep0 -> it.controlGRVForeignId = targetSerialNumber
                                is ControlGRVStepBusiness.ControlGRVStep1 -> it.controlGRVForeignId = targetSerialNumber
                                is ControlGRVStepBusiness.ControlGRVStep2 -> it.controlGRVForeignId = targetSerialNumber
                                is ControlGRVStepBusiness.ControlGRVStep3 -> it.controlGRVForeignId = targetSerialNumber
                                is ControlGRVStepBusiness.ControlGRVStep4 -> it.controlGRVForeignId = targetSerialNumber
                                is ControlGRVStepBusiness.ControlGRVStep5 -> it.controlGRVForeignId = targetSerialNumber
                                is ControlGRVStepBusiness.ControlGRVStep6 -> it.controlGRVForeignId = targetSerialNumber
                            }

                            if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(it.reference, it::class)) {
                                controlGRVStepLocalRepository.updateLocalControlGRVStep(it)
                            } else {
                                controlGRVStepLocalRepository.createLocalControlGRVStep(it)
                            }
                        }
                    }

                    saveStep(allSteps.controlGRVStep0)
                    saveStep(allSteps.controlGRVStep1)
                    saveStep(allSteps.controlGRVStep2)
                    saveStep(allSteps.controlGRVStep3)
                    saveStep(allSteps.controlGRVStep4)
                    saveStep(allSteps.controlGRVStep5)
                    saveStep(allSteps.controlGRVStep6)

                    ResultOf.Success("Commande $targetSerialNumber importée et enregistrée avec succès dans Room.")
                }
                is ResultOf.Error -> {
                    ResultOf.Error(result.exception)
                }
            }
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}
