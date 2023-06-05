package com.alicimsamil.harmonyhub.presentation.screens.firstscreen

import com.alicimsamil.harmonyhub.core.presentation.UiEvent

sealed class FirstScreenUiEvent() : UiEvent {
    object GetPagingData : FirstScreenUiEvent()
}