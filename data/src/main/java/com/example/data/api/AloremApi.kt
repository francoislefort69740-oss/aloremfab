package com.example.data.api

import com.example.data.api.modele.RemoteListGRVControl
import retrofit2.Response
import retrofit2.http.GET

interface AloremApi {
    @GET("api/commandes")
    suspend fun getControls(): Response<RemoteListGRVControl>
}