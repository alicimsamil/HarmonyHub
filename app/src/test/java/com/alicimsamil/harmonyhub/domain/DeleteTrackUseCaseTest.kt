package com.alicimsamil.harmonyhub.domain

import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.common.util.Success
import com.alicimsamil.harmonyhub.data.repository.FakeTracksRepositoryImpl
import com.alicimsamil.harmonyhub.domain.usecase.DeleteTrackUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DeleteTrackUseCaseTest {
    private lateinit var repository: FakeTracksRepositoryImpl
    private lateinit var useCase: DeleteTrackUseCase
    private lateinit var track : TrackDBModel

    @Before
    fun setup() {
        repository = FakeTracksRepositoryImpl()
        useCase = DeleteTrackUseCase(repository)
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
    fun delete_track_should_be_success() = runTest {
        repository.addTrack(track)
        Assert.assertTrue(repository.localTracks.contains(track))
        val result = useCase.invoke(track)
        Assert.assertEquals(Success(Unit), result)
        Assert.assertFalse(repository.localTracks.contains(track))
    }
}