package com.alicimsamil.harmonyhub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.common.model.TrackModel
import com.alicimsamil.harmonyhub.core.common.model.TracksModel
import com.alicimsamil.harmonyhub.core.common.util.DataResult
import com.alicimsamil.harmonyhub.core.common.util.Success
import com.alicimsamil.harmonyhub.core.common.util.Error
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import kotlinx.coroutines.flow.Flow

class FakeTracksRepositoryImpl : TracksRepository{

    val tracks = mutableListOf<TrackModel>()
    val localTracks = mutableListOf<TrackDBModel>()


    override fun getRemoteTracksWithPaging(): Flow<PagingData<TrackModel>> {
        return Pager( config = PagingConfig(pageSize = 20) ){ FakeRemotePagingSource() }.flow
    }

    override suspend fun getTracksOnNetwork(term: String): DataResult<TracksModel, String> {
        return Success(TracksModel(tracks.size, tracks))
    }

    override suspend fun addTrack(trackModel: TrackDBModel): DataResult<Unit, String> {
        localTracks.add(trackModel)
        return Success(Unit)
    }

    override suspend fun deleteTrack(trackModel: TrackDBModel): DataResult<Unit, String> {
        if (localTracks.remove(trackModel)) {
            return Success(Unit)
        }
        return Error("Note not found")
    }

    override suspend fun getTrackById(id: Int): DataResult<TrackDBModel, String> {
        val note = localTracks.find { it.id == id }
        return if (note != null) {
            Success(note)
        } else {
            Error("Note not found")
        }
    }

    override fun getLocalTracksWithPaging(): Flow<PagingData<TrackDBModel>> {
        return Pager( config = PagingConfig(pageSize = 20) ){ FakeLocalPagingSource() }.flow
    }
}