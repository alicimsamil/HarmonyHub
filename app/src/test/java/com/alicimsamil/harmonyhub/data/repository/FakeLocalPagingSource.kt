package com.alicimsamil.harmonyhub.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel

class FakeLocalPagingSource : PagingSource<Int, TrackDBModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrackDBModel> {
        if(params.key == null){
            val firstPage = listOf<TrackDBModel>()
            val nextKey  = 2
            return LoadResult.Page(
                data = firstPage ,
                prevKey = null,
                nextKey = nextKey
            )
        } else{
            val newPage = listOf<TrackDBModel>()
            val nextKey  = 2
            return LoadResult.Page(
                data = newPage ,
                prevKey = null,
                nextKey = nextKey
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TrackDBModel>): Int {
        return 0
    }
}