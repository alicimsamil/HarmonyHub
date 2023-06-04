package com.alicimsamil.harmonyhub.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alicimsamil.harmonyhub.core.common.model.TrackModel
import com.alicimsamil.harmonyhub.data.remote.service.HarmonyHubService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TracksPagingSource @Inject constructor(private val harmonyHubService: HarmonyHubService) :
    PagingSource<Int, TrackModel>() {

    override fun getRefreshKey(state: PagingState<Int, TrackModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrackModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =
                harmonyHubService.getAllTracks("jack+jackson", params.key ?: 0)
            LoadResult.Page(
                data = response.results!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}