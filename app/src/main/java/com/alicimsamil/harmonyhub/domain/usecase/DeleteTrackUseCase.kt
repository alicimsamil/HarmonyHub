package com.alicimsamil.harmonyhub.domain.usecase

import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import javax.inject.Inject

class DeleteTrackUseCase @Inject constructor(private val repository: TracksRepository) {
    suspend operator fun invoke(trackModel: TrackDBModel) = run {
        repository.deleteTrack(trackModel)
    }
}