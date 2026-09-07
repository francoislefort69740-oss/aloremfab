package com.example.data.mapper

import com.example.data.database.entities.ControlGRVLocal
import com.example.data.serialize.model.TemplateGRVLocal
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.TemplateGRVBusiness

object ControlGRVMapper {

    fun controlGRVLocalToBusiness(controlGRVLocal: ControlGRVLocal): ControlGRVBusiness = ControlGRVBusiness(
        uid = controlGRVLocal.uid,
        serialNumber = controlGRVLocal.serialNumber,
        currentStep = controlGRVLocal.currentStep,
        currentlyGoingOn = controlGRVLocal.currentlyGoingOn,
        loaded = controlGRVLocal.loaded
    )

    fun controlGRVBusinessToLocal(controlGRVBusiness: ControlGRVBusiness): ControlGRVLocal = ControlGRVLocal(
        uid = controlGRVBusiness.uid!!,
        serialNumber = controlGRVBusiness.serialNumber!!,
        currentStep = controlGRVBusiness.currentStep,
        currentlyGoingOn = controlGRVBusiness.currentlyGoingOn,
        loaded = controlGRVBusiness.loaded
    )

    fun allControlGRVLocalToBusiness(controlGRVLocals: List<ControlGRVLocal>): List<ControlGRVBusiness> {
        val result = mutableListOf<ControlGRVBusiness>()
        controlGRVLocals.forEach {
            result.add(controlGRVLocalToBusiness(it))
        }
        return result
    }

    fun getAllControlGRVLocalToBusiness(templateGRVLocal: List<TemplateGRVLocal>): List<TemplateGRVBusiness> {
        val result = mutableListOf<TemplateGRVBusiness>()
        templateGRVLocal.forEach {
            result.add(controlGRVTemplateLocalToBusiness(it))
        }
        return result
    }

    fun controlGRVTemplateLocalToBusiness(templateGRVLocal: TemplateGRVLocal?): TemplateGRVBusiness = TemplateGRVBusiness(
            name = templateGRVLocal?.name ?: "",
            x = templateGRVLocal?.x ?: 0F,
            y = templateGRVLocal?.y ?: 0F
    )

    fun controlGRVTemplateBusinessToLocal(templateGRVBusiness: TemplateGRVBusiness): TemplateGRVLocal = TemplateGRVLocal(
        name = templateGRVBusiness.name,
        x = templateGRVBusiness.x,
        y = templateGRVBusiness.y
    )

}