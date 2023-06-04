package com.alicimsamil.harmonyhub.core.common.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alicimsamil.harmonyhub.core.common.model.TableConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TrackDBModel(
    @ColumnInfo(name = "artistId")
    val artistId: Int?,
    @ColumnInfo(name = "artistName")
    val artistName: String?,
    @ColumnInfo(name = "artworkUrl100")
    val artworkUrl100: String?,
    @ColumnInfo(name = "artworkUrl30")
    val artworkUrl30: String?,
    @ColumnInfo(name = "artworkUrl60")
    val artworkUrl60: String?,
    @ColumnInfo(name = "collectionName")
    val collectionName: String?,
    @ColumnInfo(name = "collectionPrice")
    val collectionPrice: Double?,
    @ColumnInfo(name = "country")
    val country: String?,
    @ColumnInfo(name = "currency")
    val currency: String?,
    @ColumnInfo(name = "kind")
    val kind: String?,
    @ColumnInfo(name = "longDescription")
    val longDescription: String?,
    @ColumnInfo(name = "previewUrl")
    val previewUrl: String?,
    @ColumnInfo(name = "primaryGenreName")
    val primaryGenreName: String?,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String?,
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String?,
    @ColumnInfo(name = "trackId")
    val trackId: Int?,
    @ColumnInfo(name = "trackName")
    val trackName: String?,
    @ColumnInfo(name = "trackNumber")
    val trackNumber: Int?,
    @ColumnInfo(name = "trackPrice")
    val trackPrice: Double?,
    @ColumnInfo(name = "trackRentalPrice")
    val trackRentalPrice: Double?,
    @ColumnInfo(name = "trackTimeMillis")
    val trackTimeMillis: Long?,
    @ColumnInfo(name = "wrapperType")
    val wrapperType: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null
)

object TableConstants{
    const val TABLE_NAME = "track_table"
}
