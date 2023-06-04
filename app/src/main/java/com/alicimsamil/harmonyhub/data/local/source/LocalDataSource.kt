package com.alicimsamil.harmonyhub.data.local.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.data.local.db.HarmonyHubDAO
import com.alicimsamil.harmonyhub.data.local.util.BaseLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val harmonyDao: HarmonyHubDAO) :
    BaseLocalDataSource() {
    suspend fun addTrack(trackModel: TrackDBModel) =
        performDatabaseOperation {
            harmonyDao.addTrack(trackModel)
        }

    suspend fun deleteTrack(trackModel: TrackDBModel) =
        performDatabaseOperation {
            harmonyDao.deleteTrack(trackModel)
        }

    fun getLocalTracksWithPaging() : Flow<PagingData<TrackDBModel>> {
        return Pager( config = PagingConfig(pageSize = 20) ){ harmonyDao.getAllTracks() }.flow
    }

    suspend fun getTrackById(id: Int) =
        performDatabaseOperation {
            harmonyDao.getTrackById(id)
        }

}