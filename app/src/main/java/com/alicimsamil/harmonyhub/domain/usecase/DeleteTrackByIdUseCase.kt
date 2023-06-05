package com.alicimsamil.harmonyhub.domain.usecase

import com.alicimsamil.harmonyhub.core.common.util.DataResult
import com.alicimsamil.harmonyhub.core.common.util.Success
import com.alicimsamil.harmonyhub.core.common.util.Error
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class DeleteTrackByIdUseCase @Inject constructor(private val repository: TracksRepository) {
    suspend operator fun invoke(id: Int): DataResult<Unit, String> {
        val deferred = CompletableDeferred<DataResult<Unit, String>>()

        repository.getTrackById(id)
            .onSuccess {
                it?.let {
                    repository.deleteTrack(it).onSuccess {
                        deferred.complete(Success(Unit))
                    }.onFailure {
                        deferred.complete(Error(it))
                    }
                }
            }.onFailure {
                deferred.complete(Error(it))
            }

        return deferred.await()
    }
}