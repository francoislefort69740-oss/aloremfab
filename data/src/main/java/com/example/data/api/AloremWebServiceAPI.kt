package com.example.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AloremWebServiceAPI(private val client: OkHttpClient) {
    fun getAloremService(baseUrl: String): AloremApi {
        val formattedUrl = if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/"

        val retrofit = Retrofit.Builder()
            .baseUrl(formattedUrl)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        return retrofit.create(AloremApi::class.java)
    }
}
