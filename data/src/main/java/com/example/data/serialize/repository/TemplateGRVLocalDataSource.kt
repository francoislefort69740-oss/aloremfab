package com.example.data.serialize.repository

import android.content.Context
import com.example.domain.model.TemplateGRVBusiness

interface TemplateGRVLocalDataSource {
    // CREATE
    suspend fun createTemplateGRVId(templateGrv: TemplateGRVBusiness, context: Context)

    // READ
    suspend fun getTemplateGRVId(name: String, context: Context): TemplateGRVBusiness
    suspend fun getAllTemplates(context: Context): List<TemplateGRVBusiness>
    suspend fun checkIfTemplateGRVExist(name: String, context: Context): Boolean

    // UPDATE
    suspend fun updateTemplateGRVId(templateGrv: TemplateGRVBusiness, context: Context)
}