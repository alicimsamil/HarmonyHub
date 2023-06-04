package com.alicimsamil.harmonyhub.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alicimsamil.harmonyhub.BuildConfig
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel

@Database(entities = [TrackDBModel::class], version = BuildConfig.VERSION_CODE)
abstract class HarmonyHubDatabase : RoomDatabase() {
    abstract fun noteDAO(): HarmonyHubDAO
}