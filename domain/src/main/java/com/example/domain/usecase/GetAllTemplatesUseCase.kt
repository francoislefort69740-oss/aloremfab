package com.example.domain.usecase

import android.content.Context
import com.example.domain.ResultOf
import com.example.domain.model.TemplateGRVBusiness
import com.example.domain.repository.serialize.TemplateGRVLocalRepository

class GetAllTemplatesUseCase(private val templateGRVLocalRepository: TemplateGRVLocalRepository) {
    suspend operator fun invoke(context: Context): ResultOf<List<TemplateGRVBusiness>> {
        return ResultOf.Success(templateGRVLocalRepository.getAllTemplates(context = context))
    }
}