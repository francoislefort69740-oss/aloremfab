package com.example.domain.usecase

import android.content.Context
import com.example.domain.model.TemplateGRVBusiness
import com.example.domain.ResultOf
import com.example.domain.repository.serialize.TemplateGRVLocalRepository

class SaveTemplateUseCase(private val templateGRVLocalRepository: TemplateGRVLocalRepository) {
    suspend operator fun invoke(template: TemplateGRVBusiness, context: Context): ResultOf<Boolean> {
        if (templateGRVLocalRepository.checkIfTemplateGRVExist(template.name, context)) {
            templateGRVLocalRepository.updateTemplateGRVId(template, context)
            return ResultOf.Success(true)
        } else {
            templateGRVLocalRepository.createTemplateGRVId(template, context)
            return ResultOf.Success(true)
        }
    }
}