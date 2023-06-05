package com.alicimsamil.harmonyhub.presentation.screens.firstscreen

import androidx.paging.PagingData
import com.alicimsamil.harmonyhub.core.common.extensions.EMPTY
import com.alicimsamil.harmonyhub.core.presentation.UiState
import com.alicimsamil.harmonyhub.presentation.model.ListingModel
import kotlinx.coroutines.flow.Flow

data class FirstScreenUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    val pagingData: Flow<PagingData<ListingModel>>? = null
) : UiState
