package com.example.data.api.repository

import com.example.data.api.AloremWebServiceAPI
import com.example.data.mapper.RemoteControlGRVMapper
import com.example.domain.ResultOf
import com.example.domain.model.RemoteMessageBusiness
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteControlRemoteDataSourceImpl(private val webServiceAPI: AloremWebServiceAPI): DeleteControlRemoteDataSource {
    override suspend fun deleteControl(baseUrl: String, id: Int): ResultOf<RemoteMessageBusiness> = withContext(Dispatchers.IO) {
        try {
            val response = webServiceAPI.getAloremService(baseUrl).deleteControl(id = id)
            if (response.isSuccessful) {
                return@withContext ResultOf.Success(data = RemoteControlGRVMapper.getMessage(response.body()!!))
            } else {
                return@withContext ResultOf.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return@withContext ResultOf.Error(e)
        }
    }
}