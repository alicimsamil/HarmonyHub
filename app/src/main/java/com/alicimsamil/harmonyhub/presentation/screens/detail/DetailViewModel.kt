package com.alicimsamil.harmonyhub.presentation.screens.detail

import androidx.lifecycle.viewModelScope
import com.alicimsamil.harmonyhub.core.common.extensions.EMPTY
import com.alicimsamil.harmonyhub.core.presentation.BaseViewModel
import com.alicimsamil.harmonyhub.core.presentation.UiEvent
import com.alicimsamil.harmonyhub.domain.usecase.GetTrackByIdUseCase
import com.alicimsamil.harmonyhub.presentation.screens.firstscreen.FirstScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getTrackByIdUseCase: GetTrackByIdUseCase) :
    BaseViewModel() {

    var state = MutableStateFlow(DetailScreenUiState())
        private set

    private fun getTrackModel(id: Int) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            getTrackByIdUseCase.invoke(id)
                .onSuccess {
                    state.value = state.value.copy(isLoading = false, detailModel = it)
                }.onFailure {
                    state.value = state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                }
        }
    }

    override fun onEvent(event: UiEvent) {
        when(event){
            is DetailScreenUiEvent.GetTrackById->{
                getTrackModel(event.id)
            }
        }
    }
}