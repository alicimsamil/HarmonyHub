package com.alicimsamil.harmonyhub.presentation.screens.firstscreen

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.alicimsamil.harmonyhub.R
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.core.presentation.NavigateItemWithDataModelInterface
import com.alicimsamil.harmonyhub.core.presentation.TracksAdapter
import com.alicimsamil.harmonyhub.databinding.FragmentFirstBinding
import com.alicimsamil.harmonyhub.presentation.model.ListingModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment :
    BaseFragment<FragmentFirstBinding, FirstViewModel>(FragmentFirstBinding::inflate), NavigateItemWithDataModelInterface {

    override val viewModel by viewModels<FirstViewModel>()
    override var state = FirstScreenUiState()

    private lateinit var pagingAdapter: TracksAdapter

    override fun initialize() {
        super.initialize()
        viewModel.onEvent(FirstScreenUiEvent.GetPagingData)
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
                    state.pagingData?.collectLatest { data ->
                        pagingAdapter.submitData(data)
                    }
                }
            }
        }
    }

    private fun setRecyclerAdapter() {
        pagingAdapter = TracksAdapter(TracksAdapter.NoteComparator, navigateItemWithDataModelInterface = this)
        binding.rvFirst.adapter = pagingAdapter
    }

    override fun go(model: ListingModel) {
        navigate(R.id.action_firstFragment_to_detailFragment, bundle = bundleOf("ListModel" to model, "id" to -1))
    }

}