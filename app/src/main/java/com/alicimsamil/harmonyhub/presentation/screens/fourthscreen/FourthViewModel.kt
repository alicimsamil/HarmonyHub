package com.alicimsamil.harmonyhub.presentation.screens.fourthscreen

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.alicimsamil.harmonyhub.core.common.extensions.EMPTY
import com.alicimsamil.harmonyhub.core.presentation.BaseViewModel
import com.alicimsamil.harmonyhub.core.presentation.UiEvent
import com.alicimsamil.harmonyhub.domain.usecase.DeleteTrackByIdUseCase
import com.alicimsamil.harmonyhub.domain.usecase.GetTracksFromLocalUseCase
import com.alicimsamil.harmonyhub.presentation.model.AdapterTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FourthViewModel @Inject constructor(
    private val getTracksFromLocalUseCase: GetTracksFromLocalUseCase,
    private val deleteTrackUseCase: DeleteTrackByIdUseCase
) : BaseViewModel() {
    var state = MutableStateFlow(FourthScreenUiState())
        private set

    val eventFlow = MutableSharedFlow<Boolean>()


    private fun getPagingData() {
        state.value = state.value.copy(
            pagingData = getTracksFromLocalUseCase.invoke().cachedIn(viewModelScope)
                .map { value -> value.map { it.copy(type = AdapterTypeEnum.FOURTH) } }
        )
    }

    private fun deleteItem(id: Int) {
        viewModelScope.launch {
            deleteTrackUseCase.invoke(id)
                .onSuccess {
                    eventFlow.emit(true)
                }
                .onFailure {
                    state.value = state.value.copy(
                        isLoading = false,
                        error = it ?: String.EMPTY
                    )
                }
        }
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is FourthScreenUiEvent.GetPagingData -> {
                getPagingData()
            }

            is FourthScreenUiEvent.DeleteTrack -> {
                deleteItem(event.id)
            }
        }
    }
}