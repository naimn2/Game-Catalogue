package com.muflihun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Filters(

	@field:SerializedName("years")
	val years: List<YearsItem?>? = null
)