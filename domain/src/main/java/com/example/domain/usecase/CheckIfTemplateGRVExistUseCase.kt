package com.example.domain.usecase

import android.content.Context
import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.repository.serialize.TemplateGRVLocalRepository

class CheckIfTemplateGRVExistUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository,
                                     private val templateGRVLocalRepository: TemplateGRVLocalRepository) {
    suspend operator fun invoke(name: String, context: Context): ResultOf<Boolean> {
        val templates = templateGRVLocalRepository.getAllTemplates(context = context)
        try {
            val type = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = name.toInt(), type = ControlGRVStepBusiness.ControlGRVStep0::class)
                    as ControlGRVStepBusiness.ControlGRVStep0).type
            val step2 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = name.toInt(), type = ControlGRVStepBusiness.ControlGRVStep2::class)
                    as ControlGRVStepBusiness.ControlGRVStep2)

            val result = templates.find { it.name == "${type}_${step2.capacity20}_${step2.tare}" }
            return if (result != null) ResultOf.Success(templateGRVLocalRepository.checkIfTemplateGRVExist(name = name, context = context))
            else ResultOf.Error(ErrorBusiness.TemplateGRVNotFound)
        } catch (e: Exception) {
            return ResultOf.Error(ErrorBusiness.TemplateGRVNotFound)
        }
    }
}