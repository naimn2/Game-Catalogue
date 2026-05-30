package com.muflihun.core.data.source.local.entity

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.muflihun.core.domain.model.GenresItem
import com.muflihun.core.domain.model.ParentPlatformsItem
import com.muflihun.core.domain.model.PlatformsItem
import com.muflihun.core.domain.model.ShortScreenshotsItem

@Parcelize
@Entity(tableName = "game")
data class GameEntity(

    @ColumnInfo(name = "rating")
	val rating: Float? = null,

	@ColumnInfo(name = "playtime")
    val playtime: Int? = null,

//@ColumnInfo(name = "shortScreenshots")
//	val shortScreenshots: List<ShortScreenshotsItem?>? = null,

//@ColumnInfo(name = "platforms")
//	val platforms: List<PlatformsItem?>? = null,

	@ColumnInfo(name = "ratingTop")
    val ratingTop: Int? = null,

	@ColumnInfo(name = "reviewsTextCount")
    val reviewsTextCount: Int? = null,

//@ColumnInfo(name = "genres")
//	val genres: List<GenresItem?>? = null,

	@ColumnInfo(name = "saturatedColor")
    val saturatedColor: String? = null,

    @PrimaryKey
    val gameId: Int? = null,

//@ColumnInfo(name = "rating")
//	val parentPlatforms: List<ParentPlatformsItem?>? = null,

	@ColumnInfo(name = "ratingsCount")
    val ratingsCount: Int? = null,

	@ColumnInfo(name = "slug")
    val slug: String? = null,

	@ColumnInfo(name = "suggestionsCount")
    val suggestionsCount: Int? = null,

	@ColumnInfo(name = "backgroundImage")
    val backgroundImage: String? = null,

	@ColumnInfo(name = "tba")
    val tba: Boolean? = null,

	@ColumnInfo(name = "dominantColor")
    val dominantColor: String? = null,

	@ColumnInfo(name = "name")
    val name: String? = null,

	@ColumnInfo(name = "updated")
    val updated: String? = null,

	@ColumnInfo(name = "reviewsCount")
    val reviewsCount: Int? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

) : Parcelable