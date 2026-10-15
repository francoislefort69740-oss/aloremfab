package com.example.data.api.repository

import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.RemoteMessageBusiness

interface UpdateControlRemoteDataSource {
    suspend fun updateControl(baseUrl: String, id: Int, control: ControlGRVBusiness, allSteps: ControlGRVStepBusiness.ControlGRVAllStep): ResultOf<RemoteMessageBusiness>
}