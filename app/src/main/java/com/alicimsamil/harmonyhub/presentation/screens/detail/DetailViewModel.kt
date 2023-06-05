package com.alicimsamil.harmonyhub.presentation.screens.detail

import com.alicimsamil.harmonyhub.core.presentation.BaseViewModel
import com.alicimsamil.harmonyhub.core.presentation.UiEvent
import com.alicimsamil.harmonyhub.domain.usecase.GetTrackByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getTrackByIdUseCase: GetTrackByIdUseCase) :
    BaseViewModel() {

    override fun onEvent(event: UiEvent) {
        TODO("Not yet implemented")
    }
}