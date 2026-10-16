package com.example.data.api.repository

import com.example.data.api.modele.RemoteGRVControl
import com.example.domain.ResultOf

interface GetControlByIdRemoteDataSource {
    suspend fun getControlById(baseUrl: String, id: Int): ResultOf<RemoteGRVControl>
}
