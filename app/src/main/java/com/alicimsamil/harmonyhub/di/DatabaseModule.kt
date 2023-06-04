package com.alicimsamil.harmonyhub.di

import android.content.Context
import androidx.room.Room
import com.alicimsamil.harmonyhub.BuildConfig
import com.alicimsamil.harmonyhub.data.local.db.HarmonyHubDAO
import com.alicimsamil.harmonyhub.data.local.db.HarmonyHubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): HarmonyHubDatabase =
        Room.databaseBuilder(context, HarmonyHubDatabase::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNoteDAO(noteDatabase: HarmonyHubDatabase): HarmonyHubDAO =
        noteDatabase.noteDAO()
}