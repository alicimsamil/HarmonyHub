package com.alicimsamil.harmonyhub.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.alicimsamil.harmonyhub.BuildConfig
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
            getAndSaveTracksUseCase.invoke(BuildConfig.SEARCH_KEYWORD)
                .onSuccess {
                    delay(750.milliseconds)
                    state.value = state.value.copy(splashAnimationState = false)
                }
                .onFailure {
                    delay(750.milliseconds)
                    state.value = state.value.copy(splashAnimationState = false, error = it ?: String.EMPTY)
                }
        }
    }

    override fun onEvent(event: UiEvent) {}
}