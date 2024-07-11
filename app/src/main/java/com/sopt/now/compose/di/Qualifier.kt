package com.sopt.now.compose.di

import com.sopt.now.compose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object Qualifier {
    @AuthBaseUrl
    @Provides
    fun provideAuthBaseUrl(): String = BuildConfig.AUTH_BASE_URL

    @FollowerBaseUrl
    @Provides
    fun provideFriendBaseUrl(): String = BuildConfig.FOLLOWER_BASE_URL
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AuthBaseUrl

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FollowerBaseUrl