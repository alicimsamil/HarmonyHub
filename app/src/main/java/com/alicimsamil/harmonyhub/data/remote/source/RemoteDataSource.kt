package com.alicimsamil.harmonyhub.data.remote.source

import com.alicimsamil.harmonyhub.data.remote.service.HarmonyHubService
import com.alicimsamil.harmonyhub.data.remote.util.BaseRemoteDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val harmonyHubService: HarmonyHubService) :
    BaseRemoteDataSource() {

    suspend fun getTracks(term: String) {
        performApiCall {
            harmonyHubService.getTracks(term)
        }
    }
}