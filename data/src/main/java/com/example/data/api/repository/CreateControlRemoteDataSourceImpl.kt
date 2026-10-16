package com.example.data.api.repository

import com.example.data.api.AloremWebServiceAPI
import com.example.data.mapper.RemoteControlGRVMapper
import com.example.domain.ResultOf
import com.example.domain.model.ControlGRVBusiness
import com.example.domain.model.ControlGRVStepBusiness
import com.example.domain.model.RemoteMessageBusiness
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateControlRemoteDataSourceImpl(private val webServiceAPI: AloremWebServiceAPI): CreateControlRemoteDataSource {
    override suspend fun createControl(baseUrl: String, control: ControlGRVBusiness, allSteps: ControlGRVStepBusiness.ControlGRVAllStep): ResultOf<RemoteMessageBusiness> = withContext(Dispatchers.IO) {
        try {
            val response = webServiceAPI.getAloremService(baseUrl).createControl(
                control = RemoteControlGRVMapper.businessControlGRVToRemote(
                    controlGRVBusiness = control,
                    controlGRVStepBusiness = allSteps
                )
            )
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return@withContext ResultOf.Success(data = RemoteControlGRVMapper.getMessage(body))
            } else {
                val errorMsg = response.message().ifEmpty { "Erreur HTTP ${response.code()}" }
                return@withContext ResultOf.Error(Exception(errorMsg))
            }
        } catch (e: Exception) {
            return@withContext ResultOf.Error(e)
        }
    }
}
