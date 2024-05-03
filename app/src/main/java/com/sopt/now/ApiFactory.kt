package com.sopt.now

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import java.io.IOException

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val userRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(HeaderInterceptor()))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    private fun provideOkHttpClient(interceptor: HeaderInterceptor): OkHttpClient
            = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
        build()
    }
    class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val accessToken = LoginActivity.prefs.getString("userId", "")
            Log.d("Login", accessToken)
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
    val followerService = ApiFactory.create<FollwerService>()
}
