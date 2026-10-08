package com.example.data.api.repository

import com.example.data.api.AloremWebServiceAPI
import com.example.domain.ResultOf
import com.example.domain.model.BusinessRemoteListGRVControl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetControlsRemoteDataSourceImpl(private val webServiceAPI: AloremWebServiceAPI): GetControlsRemoteDataSource {
    override suspend fun getControlsFromApi(baseUrl: String): ResultOf<BusinessRemoteListGRVControl> =
        withContext(Dispatchers.IO) {
            try {
                val response = webServiceAPI.getAloremService(baseUrl).getControls()
                if (response.isSuccessful) {
                    return@withContext ResultOf.Success(response.body())
                } else {
                    return@withContext ResultOf.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultOf.Error(e)
            }
        }
}