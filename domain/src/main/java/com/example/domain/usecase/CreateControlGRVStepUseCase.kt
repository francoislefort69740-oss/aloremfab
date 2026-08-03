package com.example.domain.usecase

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.utils.GRVControlStepEnum
import com.example.domain.utils.getEmptyControlGRVStep

class CreateControlGRVStepUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository, private val controlGRVLocalRepository: ControlGRVLocalRepository) {
    suspend operator fun invoke(step: ControlGRVStepBusiness, controlGRVBusiness: ControlGRVBusiness): ResultOf<ControlGRVStepBusiness> {
        return try {
            when (step) {
                is ControlGRVStepBusiness.ControlGRVStep0 -> {/*
                    if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(step.reference, ControlGRVStepBusiness.ControlGRVStep0::class))  {
                        val result = controlGRVStepLocalRepository.updateLocalControlGRVStep(controlGRVStepBusiness = step)
                        if (result) ResultOf.Success(step)
                        else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                    }
                    else {
                        if (controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = step.reference)) {
                            val result = controlGRVStepLocalRepository.createLocalControlGRVStep(controlGRVStepBusiness = step)
                            if (result) {
                                if (step.isValid()) {
                                    controlGRVBusiness.currentStep = 1
                                    val controlUpdated = controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = controlGRVBusiness)
                                    if (controlUpdated != 0) ResultOf.Success(step)
                                    else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                                } else {
                                    ResultOf.Success(step)
                                }
                            }
                            else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)

                        } else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                    }*/


                    if (controlGRVLocalRepository.checkIfControlGRVExist(controlGRVId = step.reference)) {
                        val result = if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(step.reference, ControlGRVStepBusiness.ControlGRVStep0::class)) {
                            controlGRVStepLocalRepository.updateLocalControlGRVStep(controlGRVStepBusiness = step)
                        } else {
                            controlGRVStepLocalRepository.createLocalControlGRVStep(controlGRVStepBusiness = step)
                        }
                            if (result) {
                                if (step.isValid()) {
                                    controlGRVBusiness.currentStep = 1
                                    val controlUpdated = controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = controlGRVBusiness)
                                    if (controlUpdated != 0) ResultOf.Success(step)
                                    else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                                } else {
                                    ResultOf.Success(step)
                                }
                            } else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)

                        } else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)

                }
                is ControlGRVStepBusiness.ControlGRVStep1 -> {/*
                    if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(step.reference, ControlGRVStepBusiness.ControlGRVStep1::class))  {
                        val result = controlGRVStepLocalRepository.updateLocalControlGRVStep(controlGRVStepBusiness = step)
                        if (result) ResultOf.Success(step)
                        else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                    }
                    else {
                        val result = controlGRVStepLocalRepository.createLocalControlGRVStep(controlGRVStepBusiness = step)
                        if (result) {
                            if (step.isValid()) {
                                controlGRVBusiness.currentStep = 2
                                val controlUpdated = controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = controlGRVBusiness)
                                if (controlUpdated != 0) ResultOf.Success(step)
                                else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                            } else {
                                ResultOf.Success(step)
                            }
                        }
                        else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)
                    }*/

                    val result = if (controlGRVStepLocalRepository.checkIfControlGRVStepExist(step.reference, ControlGRVStepBusiness.ControlGRVStep1::class))  {
                        controlGRVStepLocalRepository.updateLocalControlGRVStep(controlGRVStepBusiness = step)
                    } else {
                        controlGRVStepLocalRepository.createLocalControlGRVStep(controlGRVStepBusiness = step)
                    }

                    if (result) {
                        if (step.isValid()) {
                            controlGRVBusiness.currentStep = 2
                            val controlUpdated = controlGRVLocalRepository.updateLocalControlGRV(controlGRVBusiness = controlGRVBusiness)
                            if (controlUpdated != 0) ResultOf.Success(step)
                            else ResultOf.Error(ErrorBusiness.ControlGRVNotFound)
                        } else {
                            ResultOf.Success(step)
                        }
                    } else ResultOf.Error(ErrorBusiness.ControlGRVStepNotFound)

                }
            }
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}