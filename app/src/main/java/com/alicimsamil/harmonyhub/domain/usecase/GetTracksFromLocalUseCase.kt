package com.alicimsamil.harmonyhub.domain.usecase

import androidx.paging.map
import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.domain.BaseMapper
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import com.alicimsamil.harmonyhub.presentation.model.ListingModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTracksFromLocalUseCase @Inject constructor(private val repository: TracksRepository) :
    BaseMapper<TrackDBModel, ListingModel>() {
    operator fun invoke() = run {
        repository.getLocalTracksWithPaging().map { value -> value.map { map(it) } }
    }

    override fun map(source: TrackDBModel): ListingModel {
        source.apply {
            return ListingModel(
                trackId = trackId,
                artistName = artistName,
                price = "${trackPrice ?: 0} $currency",
                kind = kind,
                primaryGenreName = primaryGenreName,
                trackName = trackName,
                collectionName = collectionName,
                image = artworkUrl100,
                releaseDate = releaseDate
            )
        }
    }
}