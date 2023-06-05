package com.alicimsamil.harmonyhub.presentation.screens.thirdscreen

import com.alicimsamil.harmonyhub.core.presentation.UiEvent

sealed class ThirdScreenUiEvent() : UiEvent {
    object GetPagingData : ThirdScreenUiEvent()
}