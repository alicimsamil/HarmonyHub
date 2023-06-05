package com.alicimsamil.harmonyhub.presentation.screens.fourthscreen

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.databinding.FragmentFourthBinding
import com.alicimsamil.harmonyhub.core.presentation.TracksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FourthFragment : BaseFragment<FragmentFourthBinding, FourthViewModel>(FragmentFourthBinding::inflate) {
    override val viewModel by viewModels<FourthViewModel>()
    override var state = FourthScreenUiState()

    private lateinit var pagingAdapter : TracksAdapter

    override fun initialize() {
        super.initialize()
        viewModel.onEvent(FourthScreenUiEvent.GetPagingData)
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
                    state.pagingData?.collectLatest {data->
                        pagingAdapter.submitData(data)
                    }
                }
            }
        }
    }

    private fun setRecyclerAdapter() {
        pagingAdapter = TracksAdapter(TracksAdapter.NoteComparator)
        binding.rvFourth.adapter = pagingAdapter
    }

}