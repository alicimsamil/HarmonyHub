package com.alicimsamil.harmonyhub.presentation.screens.firstscreen

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.alicimsamil.harmonyhub.core.presentation.BaseViewModel
import com.alicimsamil.harmonyhub.core.presentation.UiEvent
import com.alicimsamil.harmonyhub.domain.usecase.GetTracksFromRemoteUseCase
import com.alicimsamil.harmonyhub.presentation.model.AdapterTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(private val getTracksFromRemoteUseCase: GetTracksFromRemoteUseCase) :
    BaseViewModel() {

    var state = MutableStateFlow(FirstScreenUiState())
        private set

    private fun getPagingData() {
        state.value = state.value.copy(
            pagingData = getTracksFromRemoteUseCase.invoke().cachedIn(viewModelScope)
                .map { value -> value.map { it.copy(type = AdapterTypeEnum.FIRST) } }
        )
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is FirstScreenUiEvent.GetPagingData -> {
                getPagingData()
            }
        }
    }
}