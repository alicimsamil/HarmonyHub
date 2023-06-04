package com.alicimsamil.harmonyhub.presentation.model

data class DetailModel(
    val trackId: Int?,
    val artistName: String?,
    val price: String?,
    val kind: String?,
    val primaryGenreName: String?,
    val trackName: String?,
    val collectionName: String?,
    val image100: String?,
    val image30: String? = null,
    val image60: String? = null,
    val releaseDate: String?,
    val previewUrl: String?,
    val trackTimeMillis: String?,
    val country: String? = null,
    val trackRentalPrice: String? = null
)
