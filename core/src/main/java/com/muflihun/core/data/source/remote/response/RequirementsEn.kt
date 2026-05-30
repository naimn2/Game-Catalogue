package com.muflihun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RequirementsEn(

	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
)