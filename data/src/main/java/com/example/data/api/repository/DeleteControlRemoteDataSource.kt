package com.example.data.api.repository

import com.example.domain.ResultOf
import com.example.domain.model.RemoteMessageBusiness

interface DeleteControlRemoteDataSource {
    suspend fun deleteControl(baseUrl: String, id: Int): ResultOf<RemoteMessageBusiness>
}