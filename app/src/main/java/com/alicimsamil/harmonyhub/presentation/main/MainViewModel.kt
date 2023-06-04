package com.alicimsamil.harmonyhub.presentation.main

import androidx.lifecycle.viewModelScope
import com.alicimsamil.harmonyhub.core.common.extensions.EMPTY
import com.alicimsamil.harmonyhub.core.presentation.BaseViewModel
import com.alicimsamil.harmonyhub.core.presentation.UiEvent
import com.alicimsamil.harmonyhub.domain.usecase.GetAndSaveTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class MainViewModel @Inject constructor(private val getAndSaveTracksUseCase: GetAndSaveTracksUseCase) :
    BaseViewModel() {

    var state = MutableStateFlow(MainUiState())
        private set

    init {
        getSplashAnimationState()
    }

    private fun getSplashAnimationState() {
        viewModelScope.launch {
            getAndSaveTracksUseCase.invoke("jack+johnson")
                .onSuccess {
                    delay(750.milliseconds)
                    state.value = state.value.copy(splashAnimationState = false)
                }
                .onFailure {
                    state.value = state.value.copy(error = it ?: String.EMPTY)
                    delay(750.milliseconds)
                    state.value = state.value.copy(splashAnimationState = false)
                }
        }
    }

    override fun onEvent(event: UiEvent) {}
}