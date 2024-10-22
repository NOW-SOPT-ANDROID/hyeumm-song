package com.sopt.now.compose.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import com.sopt.now.compose.data.remote.service.AuthService
import com.sopt.now.compose.data.remote.service.UserService
import com.sopt.now.compose.feature.auth.signin.SigninActivity
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import java.io.IOException

@Module
@InstallIn(SingletonComponent::class)
object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL
    private const val USER_ID: String = "userId"
    private const val CONTENT_TYPE = "application/json"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }

    val userRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(HeaderInterceptor()))
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }

    private fun provideOkHttpClient(interceptor: HeaderInterceptor): OkHttpClient =
        OkHttpClient.Builder().run {
            addInterceptor(interceptor)
            build()
        }

    class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val accessToken = SigninActivity.prefs.getString(USER_ID, "")
            val newRequest = request().newBuilder()
                .addHeader("memberId", accessToken)
                .build()
            proceed(newRequest)
        }
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
    inline fun <reified T> createUser(): T = userRetrofit.create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val userService = ApiFactory.createUser<UserService>()
}