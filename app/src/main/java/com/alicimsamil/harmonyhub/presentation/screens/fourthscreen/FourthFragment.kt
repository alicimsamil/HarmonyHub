package com.alicimsamil.harmonyhub.presentation.screens.fourthscreen

import android.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alicimsamil.harmonyhub.R
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.core.presentation.DeleteItemInterface
import com.alicimsamil.harmonyhub.core.presentation.NavigateItemInterface
import com.alicimsamil.harmonyhub.databinding.FragmentFourthBinding
import com.alicimsamil.harmonyhub.core.presentation.TracksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FourthFragment :
    BaseFragment<FragmentFourthBinding, FourthViewModel>(FragmentFourthBinding::inflate), NavigateItemInterface, DeleteItemInterface {
    override val viewModel by viewModels<FourthViewModel>()
    override var state = FourthScreenUiState()

    private lateinit var pagingAdapter: TracksAdapter

    override fun initialize() {
        super.initialize()
        viewModel.onEvent(FourthScreenUiEvent.GetPagingData)
        setRecyclerAdapter()
        observeUiState()
        showDeletedSuccessPopUp()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    state = it
                    state.pagingData?.collectLatest { data ->
                        pagingAdapter.submitData(data)
                    }
                    handleFailure()
                    handleLoading()
                }
            }
        }
    }

    private fun setRecyclerAdapter() {
        pagingAdapter = TracksAdapter(TracksAdapter.NoteComparator, navigateItemInterface = this, deleteItemInterface = this)
        binding.rvFourth.adapter = pagingAdapter
    }

    private fun showDeletedSuccessPopUp() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collectLatest {
                    if (it) {
                        AlertDialog.Builder(context)
                            .setTitle(getString(R.string.delete))
                            .setMessage(getString(R.string.delete_success))
                            .create()
                            .show()
                        viewModel.eventFlow.emit(false)
                    }
                }
            }
        }
    }

    override fun delete(id: Int) {
        viewModel.onEvent(FourthScreenUiEvent.DeleteTrack(id))
    }

    override fun go(id: Int) {
        navigate(R.id.action_fourthFragment_to_detailFragment, bundle = bundleOf("id" to id))
    }
}