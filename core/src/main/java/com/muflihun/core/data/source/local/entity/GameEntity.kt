package com.muflihun.core.data.source.local.entity

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muflihun.core.data.source.remote.response.TagsItem

@Parcelize
@Entity(tableName = "game")
data class GameEntity(

    @ColumnInfo(name = "rating")
	val rating: Float? = null,

	@ColumnInfo(name = "playtime")
    val playtime: Int? = null,

    @ColumnInfo(name = "shortScreenshots")
	val shortScreenshots: String? = null,

    @ColumnInfo(name = "tags")
    val tags: String? = null,

    @ColumnInfo(name = "platforms")
	val platforms: String? = null,

	@ColumnInfo(name = "ratingTop")
    val ratingTop: Int? = null,

	@ColumnInfo(name = "reviewsTextCount")
    val reviewsTextCount: Int? = null,

    @ColumnInfo(name = "genres")
	val genres: String? = null,

	@ColumnInfo(name = "saturatedColor")
    val saturatedColor: String? = null,

    @PrimaryKey
    val gameId: Int? = null,

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