package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.service.FollowerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideFriendService(
        retrofitBuilder: Retrofit.Builder,
        @FollowerBaseUrl url: String,
    ): FollowerService {
        return retrofitBuilder.baseUrl(url).build().create(FollowerService::class.java)
    }
}