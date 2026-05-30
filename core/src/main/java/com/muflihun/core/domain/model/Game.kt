package com.muflihun.core.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Game(
	val added: Int? = null,
	val rating: Float? = null,
	val playtime: Int? = null,
	val shortScreenshots: List<ShortScreenshotsItem?>? = null,
	val platforms: String? = null,
	val ratingTop: Int? = null,
	val reviewsTextCount: Int? = null,
	val genres: String? = null,
	val saturatedColor: String? = null,
	val id: Int? = null,
	val parentPlatforms: List<ParentPlatformsItem?>? = null,
	val ratingsCount: Int? = null,
	val slug: String? = null,
	val suggestionsCount: Int? = null,
	val backgroundImage: String? = null,
	val tba: Boolean? = null,
	val dominantColor: String? = null,
	val name: String? = null,
	val updated: String? = null,
	val reviewsCount: Int? = null,
	val isFavorite: Boolean = false,
) : Parcelable