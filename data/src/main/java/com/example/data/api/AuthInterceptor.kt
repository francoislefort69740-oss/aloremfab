package com.example.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        return chain.proceed(getProdRequest(chainRequest))
    }

    private fun getProdRequest(chainRequest: Request): Request {
        return chainRequest.newBuilder().build()
    }
}