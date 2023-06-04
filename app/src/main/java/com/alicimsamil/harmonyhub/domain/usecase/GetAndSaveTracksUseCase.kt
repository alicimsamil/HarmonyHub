package com.alicimsamil.harmonyhub.domain.usecase

import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.common.model.TrackModel
import com.alicimsamil.harmonyhub.core.common.util.DataResult
import com.alicimsamil.harmonyhub.core.common.util.Success
import com.alicimsamil.harmonyhub.core.common.util.Error
import com.alicimsamil.harmonyhub.core.domain.BaseMapper
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class GetAndSaveTracksUseCase @Inject constructor(
    private val repository: TracksRepository,
) : BaseMapper<TrackModel, TrackDBModel>() {
    suspend operator fun invoke(term: String): DataResult<Unit, String> {
        val deferred = CompletableDeferred<DataResult<Unit, String>>()
        repository.getTracksOnNetwork(term)
            .onSuccess { tracksModel ->
                tracksModel?.let { model ->
                    model.results?.let { list ->
                        list.forEachIndexed { index, trackModel ->
                            repository.addTrack(map(trackModel))
                                .onFailure {
                                    deferred.complete(Error(it))
                                }
                                .onSuccess {
                                    if (index == list.size - 1) {
                                        deferred.complete(Success(Unit))
                                    }
                                }
                        }
                    }
                }
            }.onFailure {
                deferred.complete(Error(it))
            }
        return deferred.await()
    }


    override fun map(source: TrackModel): TrackDBModel {
        source.apply {
            return TrackDBModel(
                artistId = artistId,
                artistName = artistName,
                artworkUrl100 = artworkUrl100,
                artworkUrl30 = artworkUrl30,
                artworkUrl60 = artworkUrl60,
                collectionName = collectionName,
                collectionPrice = collectionPrice,
                country = country,
                currency = currency,
                kind = kind,
                longDescription = longDescription,
                previewUrl = previewUrl,
                primaryGenreName = primaryGenreName,
                releaseDate = releaseDate,
                shortDescription = shortDescription,
                trackId = trackId,
                trackName = trackName,
                trackNumber = trackNumber,
                trackPrice = trackPrice,
                trackRentalPrice = trackRentalPrice,
                trackTimeMillis = trackTimeMillis,
                wrapperType = wrapperType
            )
        }
    }
}
