package com.alicimsamil.harmonyhub.data.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alicimsamil.harmonyhub.core.common.model.TableConstants.TABLE_NAME
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel

@Dao
interface HarmonyHubDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrack(trackModel: TrackDBModel)

    @Delete
    suspend fun deleteTrack(trackModel: TrackDBModel)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllTracks(): PagingSource<Int, TrackDBModel>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getTrackById(id: Int): TrackDBModel
}