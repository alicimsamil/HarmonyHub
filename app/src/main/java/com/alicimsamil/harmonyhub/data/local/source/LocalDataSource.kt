package com.alicimsamil.harmonyhub.data.local.source

import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.data.local.db.HarmonyHubDAO
import com.alicimsamil.harmonyhub.data.local.util.BaseLocalDataSource
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val harmonyDao: HarmonyHubDAO) :
    BaseLocalDataSource() {
    suspend fun addTrack(trackModel: TrackDBModel) {
        performDatabaseOperation {
            harmonyDao.addTrack(trackModel)
        }
    }

    suspend fun deleteTrack(trackModel: TrackDBModel) {
        performDatabaseOperation {
            harmonyDao.deleteTrack(trackModel)
        }
    }

    fun getAllTracks() = harmonyDao.getAllTracks()

    suspend fun getTrackById(id: Int) {
        performDatabaseOperation {
            harmonyDao.getTrackById(id)
        }
    }
}