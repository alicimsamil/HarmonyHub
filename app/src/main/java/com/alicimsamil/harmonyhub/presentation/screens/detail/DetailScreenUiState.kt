package com.alicimsamil.harmonyhub.presentation.screens.detail

import com.alicimsamil.harmonyhub.core.common.extensions.EMPTY
import com.alicimsamil.harmonyhub.core.presentation.UiState
import com.alicimsamil.harmonyhub.presentation.model.DetailModel

data class DetailScreenUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    var detailModel: DetailModel? = null
) : UiState