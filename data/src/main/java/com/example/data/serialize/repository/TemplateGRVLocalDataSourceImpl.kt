package com.example.data.serialize.repository

import android.content.Context
import android.net.Uri
import com.example.data.mapper.ControlGRVMapper
import com.example.data.serialize.dao.TemplateGRVDao
import com.example.domain.model.TemplateGRVBusiness

class TemplateGRVLocalDataSourceImpl(private val templateGRVDao: TemplateGRVDao): TemplateGRVLocalDataSource {
    override suspend fun createTemplateGRVId(templateGrv: TemplateGRVBusiness, context: Context) {
        templateGRVDao.createTemplate(ControlGRVMapper.controlGRVTemplateBusinessToLocal(templateGRVBusiness =  templateGrv), context = context)
    }

    override suspend fun getTemplateGRVId(name : String, context: Context): TemplateGRVBusiness {
        return ControlGRVMapper.controlGRVTemplateLocalToBusiness(templateGRVLocal = templateGRVDao.getTemplate(name = name, context = context))
    }

    override suspend fun getAllTemplates(context: Context): List<TemplateGRVBusiness> {
        return ControlGRVMapper.getAllControlGRVLocalToBusiness(templateGRVDao.getAllTemplates(context = context))
    }

    override suspend fun checkIfTemplateGRVExist(name: String, context: Context): Boolean {
        return templateGRVDao.templateExists(name = name, context = context)
    }

    override suspend fun updateTemplateGRVId(templateGrv: TemplateGRVBusiness, context: Context) {
        templateGRVDao.updateTemplate(ControlGRVMapper.controlGRVTemplateBusinessToLocal(templateGRVBusiness =  templateGrv), context = context)
    }

}