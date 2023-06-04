package com.alicimsamil.harmonyhub.domain

import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.data.repository.FakeTracksRepositoryImpl
import com.alicimsamil.harmonyhub.domain.usecase.GetTrackByIdUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetTrackByIdUseCaseTest {
    private lateinit var repository: FakeTracksRepositoryImpl
    private lateinit var useCase: GetTrackByIdUseCase
    private lateinit var track : TrackDBModel

    @Before
    fun setup() {
        repository = FakeTracksRepositoryImpl()
        useCase = GetTrackByIdUseCase(repository)
        track = TrackDBModel(
            191827891,
            "Ali Şamil Küçük",
            "www.google.com",
            null,
            null,
            "Ayna" ,
            44.44,
            "TR",
            "TRY",
            "song",
            null,
            null,
            null,
            "13.02.2000",
            null,
            125125125,
            null,
            null,
            null,
            null,
            null,
            null,
            1
        )
    }

    @Test
    fun get_track_by_id_should_be_success() = runTest {
        repository.addTrack(track)
        assertTrue(repository.localTracks.contains(track))
        val result = useCase.invoke(track.trackId ?:0 )
        result.onSuccess {
            assertEquals(track.trackId, it?.trackId)
        }
    }
}