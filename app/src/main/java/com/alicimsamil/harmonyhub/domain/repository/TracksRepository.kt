package com.alicimsamil.harmonyhub.domain.repository

import androidx.paging.PagingData
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.common.model.TrackModel
import com.alicimsamil.harmonyhub.core.common.model.TracksModel
import com.alicimsamil.harmonyhub.core.common.util.DataResult
import kotlinx.coroutines.flow.Flow

interface TracksRepository {
    fun getRemoteTracksWithPaging(): Flow<PagingData<TrackModel>>

    suspend fun getTracksOnNetwork(term: String): DataResult<TracksModel, String>

    suspend fun addTrack(trackModel: TrackDBModel): DataResult<Unit, String>

    suspend fun deleteTrack(trackModel: TrackDBModel): DataResult<Unit, String>

    suspend fun getTrackById(id: Int): DataResult<TrackDBModel, String>

    fun getLocalTracksWithPaging() : Flow<PagingData<TrackDBModel>>
}