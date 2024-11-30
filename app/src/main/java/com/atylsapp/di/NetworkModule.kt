package com.atylsapp.di

import com.atylsapp.BuildConfig
import com.atylsapp.network.AuthInterceptor
import com.atylsapp.network.MovieApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_MS = 60_000L

    @Provides
    @Singleton
    fun providesOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .readTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .writeTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .connectTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(
        gson: Gson,
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun providesMovieApiService(
        retrofit: Retrofit
    ): MovieApiService {
        return retrofit.create()
    }

    @Provides
    @Named("base_url")
    fun providesBaseUrl(): String {
        return  BuildConfig.BASE_URL
    }

    @Provides
    @Named("api_key")
    fun providesApikey(): String {
        return  BuildConfig.API_KEY
    }
}