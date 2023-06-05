package com.alicimsamil.harmonyhub.presentation.screens.detail

import android.media.MediaPlayer
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.alicimsamil.harmonyhub.core.common.extensions.invisible
import com.alicimsamil.harmonyhub.core.common.extensions.loadUrl
import com.alicimsamil.harmonyhub.core.common.extensions.visible
import com.alicimsamil.harmonyhub.core.presentation.BaseFragment
import com.alicimsamil.harmonyhub.databinding.FragmentDetailBinding
import com.alicimsamil.harmonyhub.presentation.model.DetailModel
import com.alicimsamil.harmonyhub.presentation.model.ListingModel
import com.alicimsamil.harmonyhub.presentation.screens.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailViewModel>(FragmentDetailBinding::inflate) {
    override val viewModel by viewModels<DetailViewModel>()
    override var state = DetailScreenUiState()
    private var mediaPlayer: MediaPlayer? = null
    private val navArgs by navArgs<DetailFragmentArgs>()

    override fun initialize() {
        super.initialize()
        removeToolbar()
        checkNavArgs()
        initListeners()
    }

    private fun removeToolbar() {
        (activity as MainActivity).hideToolbar()
    }

    private fun checkNavArgs() {
        if (navArgs.ListModel == null) {
            viewModel.onEvent(DetailScreenUiEvent.GetTrackById(navArgs.id))
            observeUiState()
        } else {
            parseStateByList(navArgs.ListModel)
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    state = it
                    handleFailure()
                    handleLoading()
                    setUI()
                }
            }
        }
    }

    private fun setUI() {
        if (state.detailModel != null) {
            state.detailModel?.let { model ->
                binding.apply {
                    ivDetailImage.loadUrl(model.image100, requireContext())
                    tvArtistName.text = model.artistName
                    tvCollectionName.text = model.collectionName
                    tvTrackName.text = model.trackName
                    tvTrackPrice.text = model.price
                    tvReleaseName.text = model.releaseDate
                }
            }
        }
    }

    private fun initListeners() {
        binding.ivPlay.setOnClickListener {
            state.detailModel?.previewUrl?.let {
                playMusic(it)
            }
        }

        binding.ivStop.setOnClickListener {
            stopMusic()
        }
    }

    private fun playMusic(url: String) {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(url)
        mediaPlayer?.prepareAsync()
        mediaPlayer?.setOnPreparedListener {
            it.start()
            binding.apply {
                ivPlay.invisible()
                ivStop.visible()
            }
        }
    }

    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        binding.apply {
            ivPlay.visible()
            ivStop.invisible()
        }
    }

    private fun parseStateByList(listingModel: ListingModel?) {
        listingModel?.let { model ->
            state = state.copy(
                detailModel = DetailModel(
                    trackId = model.trackId,
                    artistName = model.artistName,
                    price = model.price,
                    kind = model.kind,
                    primaryGenreName = model.primaryGenreName,
                    collectionName = model.collectionName,
                    image100 = model.image,
                    trackName = model.trackName,
                    image30 = null,
                    image60 = null,
                    releaseDate = model.releaseDate,
                    previewUrl = model.previewUrl,
                    trackTimeMillis = model.trackTimeMillis,
                    country = null,
                    trackRentalPrice = null
                )
            )
            setUI()
        }
    }

    override fun onPause() {
        (activity as MainActivity).showToolbar()
        stopMusic()
        super.onPause()
    }


}