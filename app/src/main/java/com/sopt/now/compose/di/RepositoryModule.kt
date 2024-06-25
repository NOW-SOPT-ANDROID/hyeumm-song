package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.repositoryimpl.FollowerRepositoryImpl
import com.sopt.now.compose.domain.repository.FollowerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFollowerRepository(followerRepositoryImpl: FollowerRepositoryImpl): FollowerRepository
}