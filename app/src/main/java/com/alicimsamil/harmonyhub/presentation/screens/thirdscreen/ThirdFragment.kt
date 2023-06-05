package com.alicimsamil.harmonyhub.presentation.screens.thirdscreen

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alicimsamil.harmonyhub.R
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.core.presentation.NavigateItemInterface
import com.alicimsamil.harmonyhub.databinding.FragmentThirdBinding
import com.alicimsamil.harmonyhub.core.presentation.TracksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ThirdFragment :
    BaseFragment<FragmentThirdBinding, ThirdViewModel>(FragmentThirdBinding::inflate), NavigateItemInterface {
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
        pagingAdapter = TracksAdapter(TracksAdapter.NoteComparator, navigateItemInterface = this)
        binding.rvThird.adapter = pagingAdapter
    }

    override fun go(id: Int) {
        navigate(R.id.action_thirdFragment_to_detailFragment, bundle = bundleOf("id" to id))
    }


}