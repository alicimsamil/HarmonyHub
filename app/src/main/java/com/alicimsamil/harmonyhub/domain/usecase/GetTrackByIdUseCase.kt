package com.alicimsamil.harmonyhub.domain.usecase

import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import javax.inject.Inject

class GetTrackByIdUseCase @Inject constructor(private val repository: TracksRepository) {
    suspend operator fun invoke(id: Int) = run {
        repository.getTrackById(id)
    }
}