package com.example.data.api.repository

import com.example.domain.ResultOf
import com.example.domain.model.BusinessRemoteListGRVControl

interface GetControlsRemoteDataSource {
    suspend fun getControlsFromApi(baseUrl: String): ResultOf<BusinessRemoteListGRVControl>
}