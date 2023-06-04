package com.alicimsamil.harmonyhub.data.remote.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.alicimsamil.harmonyhub.core.common.model.TrackModel
import com.alicimsamil.harmonyhub.data.remote.service.HarmonyHubService
import com.alicimsamil.harmonyhub.data.remote.util.BaseRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val harmonyHubService: HarmonyHubService,
    private val tracksPagingSource: TracksPagingSource
) :
    BaseRemoteDataSource() {

    suspend fun getTracks(term: String) {
        performApiCall {
            harmonyHubService.getTracks(term)
        }
    }

    fun getRemoteTracksWithPaging() : Flow<PagingData<TrackModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20)
        ){
            tracksPagingSource
        }.flow.map { it -> it.map { it.copy(artistId = 5) } }
    }
}