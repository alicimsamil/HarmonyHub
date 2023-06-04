package com.alicimsamil.harmonyhub.domain.usecase

import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.common.util.DataResult
import com.alicimsamil.harmonyhub.core.common.util.Success
import com.alicimsamil.harmonyhub.core.common.util.Error
import com.alicimsamil.harmonyhub.core.domain.BaseMapper
import com.alicimsamil.harmonyhub.core.domain.TimeConverter
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import com.alicimsamil.harmonyhub.presentation.model.DetailModel
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class GetTrackByIdUseCase @Inject constructor(private val repository: TracksRepository) :
    BaseMapper<TrackDBModel, DetailModel>() {
    suspend operator fun invoke(id: Int): DataResult<DetailModel, String> {
        val deferred = CompletableDeferred<DataResult<DetailModel, String>>()
        repository.getTrackById(id)
            .onSuccess {
                it?.let {
                    deferred.complete(Success(map(it)))
                }
            }.onFailure {
                deferred.complete(Error(it))
            }
        return deferred.await()
    }

    override fun map(source: TrackDBModel): DetailModel {
        with(source) {
            return DetailModel(
                trackId = trackId,
                artistName = artistName,
                price = "$trackPrice $currency",
                kind = kind,
                primaryGenreName = primaryGenreName,
                trackName = trackName,
                collectionName = collectionName,
                image100 = artworkUrl100,
                image30 = artworkUrl30,
                image60 = artworkUrl60,
                releaseDate = releaseDate,
                previewUrl = previewUrl,
                trackTimeMillis = TimeConverter.convert(trackTimeMillis ?: 0),
                country = country,
                trackRentalPrice = "$trackRentalPrice $currency"
            )
        }
    }
}