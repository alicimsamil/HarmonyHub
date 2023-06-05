package com.alicimsamil.harmonyhub.presentation.screens.main

import com.alicimsamil.harmonyhub.core.common.extensions.EMPTY
import com.alicimsamil.harmonyhub.core.presentation.UiState

data class MainUiState(
    val splashAnimationState: Boolean = true,
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY
) : UiState
