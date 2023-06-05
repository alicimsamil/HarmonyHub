package com.alicimsamil.harmonyhub.core.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.harmonyhub.core.common.extensions.loadUrl
import com.alicimsamil.harmonyhub.databinding.FirstScreenItemBinding
import com.alicimsamil.harmonyhub.databinding.FourthScreenItemBinding
import com.alicimsamil.harmonyhub.databinding.SecondScreenItemBinding
import com.alicimsamil.harmonyhub.databinding.ThirdScreenItemBinding
import com.alicimsamil.harmonyhub.presentation.model.AdapterTypeEnum
import com.alicimsamil.harmonyhub.presentation.model.ListingModel

class TracksAdapter(diffCallback: NoteComparator) :
    PagingDataAdapter<ListingModel, RecyclerView.ViewHolder>(diffCallback) {

    var context: Context? = null

    object NoteComparator : DiffUtil.ItemCallback<ListingModel>() {
        override fun areItemsTheSame(oldItem: ListingModel, newItem: ListingModel): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: ListingModel, newItem: ListingModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)?.type) {
            AdapterTypeEnum.FIRST -> ITEM_TYPE_FIRST
            AdapterTypeEnum.SECOND -> ITEM_TYPE_SECOND
            AdapterTypeEnum.THIRD -> ITEM_TYPE_THIRD
            AdapterTypeEnum.FOURTH -> ITEM_TYPE_FOURTH
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is FirstScreenViewHolder -> {
                holder.bind(item)
            }

            is SecondScreenViewHolder -> {
                holder.bind(item)
            }

            is ThirdScreenViewHolder -> {
                holder.bind(item)
            }

            is FourthScreenViewHolder -> {
                holder.bind(item)
            }

            else -> throw IllegalArgumentException("Invalid view holder type")
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        context=parent.context
        return when (viewType) {
            ITEM_TYPE_FIRST -> {
                FirstScreenViewHolder(
                    FirstScreenItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ITEM_TYPE_SECOND -> {
                SecondScreenViewHolder(
                    SecondScreenItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ITEM_TYPE_THIRD -> {
                ThirdScreenViewHolder(
                    ThirdScreenItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ITEM_TYPE_FOURTH -> {
                FourthScreenViewHolder(
                    FourthScreenItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    inner class FirstScreenViewHolder(private val binding: FirstScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteModel: ListingModel?) {
            binding.apply {
                noteModel?.let {
                    tvArtistName.text = it.artistName
                    tvTrackName.text = it.trackName
                    context?.let {context->
                        ivTrackImage.loadUrl(it.image, context)
                    }
                }
            }
        }
    }

    inner class SecondScreenViewHolder(private val binding: SecondScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteModel: ListingModel?) {
            binding.apply {
                noteModel?.let {
                    tvArtistName.text = it.artistName
                    tvTrackName.text = it.trackName
                }
            }
        }
    }

    inner class ThirdScreenViewHolder(private val binding: ThirdScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteModel: ListingModel?) {
            binding.apply {
                noteModel?.let {
                    tvArtistName.text = it.artistName
                    tvTrackName.text = it.trackName
                }
            }
        }
    }

    inner class FourthScreenViewHolder(private val binding: FourthScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteModel: ListingModel?) {
            binding.apply {
                noteModel?.let {
                    tvArtistName.text = it.artistName
                    tvTrackName.text = it.trackName
                    tvReleaseName.text = it.releaseDate
                    tvTrackPrice.text = it.price
                    context?.let {context->
                        ivTrackImage.loadUrl(it.image, context)
                    }
                }
            }
        }
    }

    companion object {
        private const val ITEM_TYPE_FIRST = 1
        private const val ITEM_TYPE_SECOND = 2
        private const val ITEM_TYPE_THIRD = 3
        private const val ITEM_TYPE_FOURTH = 4
    }
}
