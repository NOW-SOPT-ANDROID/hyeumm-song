package com.sopt.now.compose.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.data.remote.service.FollowerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val CONTENT_TYPE = "application/json"
    private val json: Json =
        Json {
            ignoreUnknownKeys = true
        }

    private fun getLogOkHttpClient(): HttpLoggingInterceptor {
        val loggingInterceptor =
            HttpLoggingInterceptor { message ->
                Log.d("Retrofit2", "CONNECTION INFO -> $message")
            }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(getLogOkHttpClient())
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
    }

    @Singleton
    @Provides
    fun provideFriendService(
        retrofitBuilder: Retrofit.Builder,
        @FollowerBaseUrl url: String,
    ): FollowerService {
        return retrofitBuilder.baseUrl(url).build().create(FollowerService::class.java)
    }
}
