package com.alicimsamil.harmonyhub.presentation.screens.detail

import androidx.fragment.app.viewModels
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(FragmentDetailBinding::inflate) {
    override val viewModel by viewModels<DetailViewModel>()
    override val state = DetailScreenUiState()

    override fun initialize() {
        super.initialize()

    }

}