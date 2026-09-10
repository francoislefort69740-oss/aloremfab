package com.example.domain.usecase

import android.content.Context
import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.ErrorBusiness
import com.example.domain.repository.db.ActiveIdLocalRepository
import com.example.domain.repository.db.ControlGRVStepLocalRepository
import com.example.domain.repository.db.UserLocalRepository
import com.example.domain.repository.serialize.TemplateGRVLocalRepository

class GetAllStepControlGRVFortReportUseCase(private val controlGRVStepLocalRepository: ControlGRVStepLocalRepository,
                                            private val userLocalRepository: UserLocalRepository,
                                            private val activeIdLocalRepository: ActiveIdLocalRepository,
                                            private val getTemplateGRVLocalRepository: TemplateGRVLocalRepository
) {
    suspend operator fun invoke(reference: Int, context: Context): ResultOf<Pair<ControlGRVStepBusiness.ControlGRVAllStep, String>> {
        return try {
            val result = ControlGRVStepBusiness.ControlGRVAllStep()
            result.controlGRVStep0 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = reference, type = ControlGRVStepBusiness.ControlGRVStep0::class)
                    as ControlGRVStepBusiness.ControlGRVStep0)
            result.controlGRVStep1 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = reference, type = ControlGRVStepBusiness.ControlGRVStep1::class)
                    as ControlGRVStepBusiness.ControlGRVStep1)
            result.controlGRVStep2 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = reference, type = ControlGRVStepBusiness.ControlGRVStep2::class)
                    as ControlGRVStepBusiness.ControlGRVStep2)
            result.controlGRVStep3 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = reference, type = ControlGRVStepBusiness.ControlGRVStep3::class)
                    as ControlGRVStepBusiness.ControlGRVStep3)
            result.controlGRVStep4 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = reference, type = ControlGRVStepBusiness.ControlGRVStep4::class)
                    as ControlGRVStepBusiness.ControlGRVStep4)
            result.controlGRVStep5 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = reference, type = ControlGRVStepBusiness.ControlGRVStep5::class)
                    as ControlGRVStepBusiness.ControlGRVStep5)
            result.controlGRVStep6 = (controlGRVStepLocalRepository
                .getLocalControlGRVStepByReference(reference = reference, type = ControlGRVStepBusiness.ControlGRVStep6::class)
                    as ControlGRVStepBusiness.ControlGRVStep6)

            val templateName = "${result.controlGRVStep0?.type}_${result.controlGRVStep2?.capacity20}_${result.controlGRVStep2?.tare}"
            if (getTemplateGRVLocalRepository.checkIfTemplateGRVExist(name = templateName, context = context)) {
                val template = getTemplateGRVLocalRepository.getTemplateGRVId(name = templateName, context = context)
                result.floatX = template.x
                result.floatY = template.y
            }
            val user = userLocalRepository.getAllUsers()
            val activeId = activeIdLocalRepository.getActiveId(user.find { it.isActive }?.uid ?: 0)
            val activeUser = userLocalRepository.getUserLocal(activeId.activeId)

            return ResultOf.Success(result to "${activeUser.firstName} ${activeUser.lastName}")
        } catch (e: Exception) {
            ResultOf.Error(ErrorBusiness.ControlGRVStepNotInitialized)
        }
    }
}