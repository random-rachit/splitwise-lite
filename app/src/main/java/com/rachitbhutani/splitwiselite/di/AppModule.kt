package com.rachitbhutani.splitwiselite.di

import com.rachitbhutani.splitwiselite.DummyExpenseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideDummyDataProvider() = DummyExpenseProvider()
}