package com.example.localnotification.di

import android.content.Context
import com.example.localnotification.manager.LocalNotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalNotificationManagerModule {

    @Provides
    @Singleton
    fun provideLocalNotificationManager(
        @ApplicationContext context: Context
    ): LocalNotificationManager {
        return LocalNotificationManager(context)
    }
}
