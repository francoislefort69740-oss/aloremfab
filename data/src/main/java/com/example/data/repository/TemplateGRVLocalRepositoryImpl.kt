package com.example.data.repository

import android.content.Context
import com.example.data.serialize.repository.TemplateGRVLocalDataSource
import com.example.domain.model.TemplateGRVBusiness
import com.example.domain.repository.serialize.TemplateGRVLocalRepository

class TemplateGRVLocalRepositoryImpl(private val templateGRVLocalDataSource: TemplateGRVLocalDataSource) : TemplateGRVLocalRepository {
    override suspend fun createTemplateGRVId(templateGrv: TemplateGRVBusiness, context: Context) {
        templateGRVLocalDataSource.createTemplateGRVId(templateGrv = templateGrv, context = context)
    }

    override suspend fun getTemplateGRVId(name: String, context: Context): TemplateGRVBusiness {
        return templateGRVLocalDataSource.getTemplateGRVId(name = name, context = context)
    }

    override suspend fun getAllTemplates(context: Context): List<TemplateGRVBusiness> {
        return templateGRVLocalDataSource.getAllTemplates(context = context)
    }

    override suspend fun checkIfTemplateGRVExist(name: String, context: Context): Boolean {
        return templateGRVLocalDataSource.checkIfTemplateGRVExist(name = name, context = context)
    }

    override suspend fun updateTemplateGRVId(templateGrv: TemplateGRVBusiness, context: Context) {
        templateGRVLocalDataSource.updateTemplateGRVId(templateGrv = templateGrv, context = context)
    }

}