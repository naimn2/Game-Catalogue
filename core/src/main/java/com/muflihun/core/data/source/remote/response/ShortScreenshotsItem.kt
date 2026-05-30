package com.muflihun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)