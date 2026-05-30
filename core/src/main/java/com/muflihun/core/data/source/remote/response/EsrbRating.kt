package com.muflihun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class EsrbRating(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)