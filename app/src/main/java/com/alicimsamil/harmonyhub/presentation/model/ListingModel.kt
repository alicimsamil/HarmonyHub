package com.alicimsamil.harmonyhub.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingModel(
    val trackId: Int?,
    val artistName: String?,
    val price: String?,
    val kind: String?,
    val primaryGenreName: String?,
    val trackName: String?,
    val collectionName: String?,
    val image: String?,
    val releaseDate: String? = null,
    val previewUrl: String? = null,
    val trackTimeMillis: String? = null,
    val type: AdapterTypeEnum? = null
) : Parcelable
