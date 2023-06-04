package com.alicimsamil.harmonyhub.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alicimsamil.harmonyhub.core.common.model.TrackModel

class FakeRemotePagingSource : PagingSource<Int, TrackModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrackModel> {
        if(params.key == null){
            val firstPage = listOf<TrackModel>()
            val nextKey  = 2
            return LoadResult.Page(
                data = firstPage ,
                prevKey = null,
                nextKey = nextKey
            )
        } else{
            val newPage = listOf<TrackModel>()
            val nextKey  = 2
            return LoadResult.Page(
                data = newPage ,
                prevKey = null,
                nextKey = nextKey
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TrackModel>): Int {
        return 0
    }
}