package com.alicimsamil.harmonyhub.data.remote.service

import com.alicimsamil.harmonyhub.core.common.model.TracksModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HarmonyHubService {

    @GET("/search")
    suspend fun getTracks(@Query(value = "term") term: String): Response<TracksModel>

    @GET("/search?limit=20")
    suspend fun getAllTracks(
        @Query(value = "term") term: String,
        @Query(value = "offset") offset: Int = 0
    ): TracksModel

}