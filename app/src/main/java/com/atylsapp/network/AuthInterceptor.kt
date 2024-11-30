package com.atylsapp.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named

class AuthInterceptor @Inject constructor(
    @Named("api_key") private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val updatedUrl = chain.request().url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val newRequest = chain.request().newBuilder()
            .url(updatedUrl)
            .build()

        return chain.proceed(newRequest)
    }
}