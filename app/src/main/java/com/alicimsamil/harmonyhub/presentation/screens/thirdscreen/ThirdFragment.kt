package com.alicimsamil.harmonyhub.presentation.screens.thirdscreen

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.databinding.FragmentThirdBinding
import com.alicimsamil.harmonyhub.core.presentation.TracksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ThirdFragment :
    BaseFragment<FragmentThirdBinding, ThirdViewModel>(FragmentThirdBinding::inflate) {
    override val viewModel by viewModels<ThirdViewModel>()
    override var state = ThirdScreenUiState()

    private lateinit var pagingAdapter : TracksAdapter

    override fun initialize() {
        super.initialize()
        viewModel.onEvent(ThirdScreenUiEvent.GetPagingData)
        setRecyclerAdapter()
        observeUiState()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    state = it
                    handleFailure()
                    handleLoading()
                    state.pagingData?.collectLatest { data->
                        pagingAdapter.submitData(data)
                    }
                }
            }
        }
    }

    private fun setRecyclerAdapter() {
        pagingAdapter = TracksAdapter(TracksAdapter.NoteComparator)
        binding.rvThird.adapter = pagingAdapter
    }


}