package com.alicimsamil.harmonyhub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.common.model.TrackModel
import com.alicimsamil.harmonyhub.data.local.source.LocalDataSource
import com.alicimsamil.harmonyhub.data.remote.source.RemoteDataSource
import com.alicimsamil.harmonyhub.data.remote.source.TracksPagingSource
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val pagingSource: TracksPagingSource
) : TracksRepository {
    override fun getRemoteTracksWithPaging(): Flow<PagingData<TrackModel>> {
        return Pager( config = PagingConfig(pageSize = 20) ){ pagingSource }.flow
    }

    override suspend fun getTracksOnNetwork(term: String) = remoteDataSource.getTracks(term)

    override suspend fun addTrack(trackModel: TrackDBModel) = localDataSource.addTrack(trackModel)

    override suspend fun deleteTrack(trackModel: TrackDBModel) = localDataSource.deleteTrack(trackModel)

    override suspend fun getTrackById(id: Int) = localDataSource.getTrackById(id)

    override fun getLocalTracksWithPaging() = localDataSource.getLocalTracksWithPaging()
}