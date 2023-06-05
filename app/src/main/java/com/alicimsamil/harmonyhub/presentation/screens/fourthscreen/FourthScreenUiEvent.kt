package com.alicimsamil.harmonyhub.presentation.screens.fourthscreen

import com.alicimsamil.harmonyhub.core.common.model.TrackDBModel
import com.alicimsamil.harmonyhub.core.presentation.UiEvent

sealed class FourthScreenUiEvent() : UiEvent {
    object GetPagingData : FourthScreenUiEvent()
    data class DeleteTrack(val listDBModel: TrackDBModel) : FourthScreenUiEvent()
}