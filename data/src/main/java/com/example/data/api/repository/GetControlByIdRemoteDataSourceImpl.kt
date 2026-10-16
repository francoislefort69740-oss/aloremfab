package com.example.data.api.repository

import com.example.data.api.AloremWebServiceAPI
import com.example.data.api.modele.RemoteGRVControl
import com.example.domain.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetControlByIdRemoteDataSourceImpl(private val webServiceAPI: AloremWebServiceAPI) : GetControlByIdRemoteDataSource {
    override suspend fun getControlById(baseUrl: String, id: Int): ResultOf<RemoteGRVControl> = withContext(Dispatchers.IO) {
        try {
            val response = webServiceAPI.getAloremService(baseUrl).getControlById(id = id)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return@withContext ResultOf.Success(data = body)
            } else {
                val errorMsg = response.message().ifEmpty { "Erreur HTTP ${response.code()}" }
                return@withContext ResultOf.Error(Exception(errorMsg))
            }
        } catch (e: Exception) {
            return@withContext ResultOf.Error(e)
        }
    }
}
