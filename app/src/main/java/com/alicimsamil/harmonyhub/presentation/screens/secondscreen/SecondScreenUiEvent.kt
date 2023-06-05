package com.alicimsamil.harmonyhub.presentation.screens.secondscreen

import com.alicimsamil.harmonyhub.core.presentation.UiEvent

sealed class SecondScreenUiEvent() : UiEvent {
    object GetPagingData : SecondScreenUiEvent()
}