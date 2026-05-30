package com.muflihun.core.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GenresItem(

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable