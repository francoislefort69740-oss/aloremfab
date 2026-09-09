package com.example.domain.usecase

import android.content.Context
import com.example.domain.ResultOf
import com.example.domain.model.TemplateGRVBusiness
import com.example.domain.repository.serialize.TemplateGRVLocalRepository

class GetTemplateUseCase(private val templateGRVLocalRepository: TemplateGRVLocalRepository) {
    suspend operator fun invoke(name: String, context: Context): ResultOf<TemplateGRVBusiness> {
        return if (templateGRVLocalRepository.checkIfTemplateGRVExist(name = name, context = context)) {
            ResultOf.Success(templateGRVLocalRepository.getTemplateGRVId(name = name, context = context))
        } else {
            ResultOf.Success(TemplateGRVBusiness(name = name, x = 50f, y = 50f))
        }
    }
}