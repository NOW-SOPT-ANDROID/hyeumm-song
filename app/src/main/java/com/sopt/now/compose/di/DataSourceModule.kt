package com.sopt.now.compose.di

import com.sopt.now.compose.data.remote.datasource.FollowerDataSource
import com.sopt.now.compose.data.remote.datasourceimpl.FollowerDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun followerDataSource(dataSourceImpl: FollowerDataSourceImpl): FollowerDataSource
}
