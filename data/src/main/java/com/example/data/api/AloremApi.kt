package com.example.data.api

import com.example.data.api.modele.RemoteGRVControl
import com.example.data.api.modele.RemoteListGRVControl
import com.example.data.api.modele.RemoteMessage
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AloremApi {
    @GET("api/commandes")
    suspend fun getControls(): Response<List<RemoteGRVControl>>

    @PUT("api/commandes/{id}")
    suspend fun updateControl(@Path("id") id : Int, @Body control: RemoteGRVControl): Response<RemoteMessage>

    @POST("api/commandes")
    suspend fun createControl(@Body control: RemoteGRVControl): Response<RemoteMessage>

    @DELETE("api/commandes/{id}")
    suspend fun deleteControl(@Path("id") id : Int): Response<RemoteMessage>
}