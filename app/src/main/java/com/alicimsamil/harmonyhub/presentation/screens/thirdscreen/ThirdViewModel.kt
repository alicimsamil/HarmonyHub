package com.alicimsamil.harmonyhub.presentation.screens.thirdscreen

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.alicimsamil.harmonyhub.core.presentation.BaseViewModel
import com.alicimsamil.harmonyhub.core.presentation.UiEvent
import com.alicimsamil.harmonyhub.domain.usecase.GetTracksFromLocalUseCase
import com.alicimsamil.harmonyhub.presentation.model.AdapterTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(private val getTracksFromLocalUseCase: GetTracksFromLocalUseCase) :
    BaseViewModel() {
    var state = MutableStateFlow(ThirdScreenUiState())
        private set

    private fun getPagingData() {
        state.value = state.value.copy(
            pagingData = getTracksFromLocalUseCase.invoke().cachedIn(viewModelScope)
                .map { value -> value.map { it.copy(type = AdapterTypeEnum.THIRD) } }
        )
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is ThirdScreenUiEvent.GetPagingData -> {
                getPagingData()
            }
        }
    }
}