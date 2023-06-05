package com.alicimsamil.harmonyhub.presentation.screens.detail

import com.alicimsamil.harmonyhub.core.presentation.UiEvent

sealed class DetailScreenUiEvent : UiEvent {
    data class GetTrackById(val id:Int) : DetailScreenUiEvent()
}