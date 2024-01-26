package com.anonymous.berlinclock.featureberlinclock.di

import com.anonymous.berlinclock.featureberlinclock.domain.usecase.GetClockData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClockModule {

    @Provides
    @Singleton
    fun provideGetClockDataUseCase(): GetClockData {
        return GetClockData()
    }
}