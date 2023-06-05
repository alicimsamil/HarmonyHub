package com.alicimsamil.harmonyhub.presentation.screens.secondscreen

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alicimsamil.harmonyhub.R
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.core.presentation.NavigateItemInterface
import com.alicimsamil.harmonyhub.databinding.FragmentSecondBinding
import com.alicimsamil.harmonyhub.core.presentation.TracksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding, SecondViewModel>(FragmentSecondBinding::inflate), NavigateItemInterface {
    override val viewModel by viewModels<SecondViewModel>()
    override var state = SecondScreenUiState()

    private lateinit var pagingAdapter : TracksAdapter

    override fun initialize() {
        super.initialize()
        viewModel.onEvent(SecondScreenUiEvent.GetPagingData)
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
        pagingAdapter = TracksAdapter(TracksAdapter.NoteComparator, navigateItemInterface = this)
        binding.rvSecond.adapter = pagingAdapter
    }

    override fun go(id: Int) {
        navigate(R.id.action_secondFragment_to_detailFragment, bundle = bundleOf("id" to id))
    }


}