package com.muflihun.core.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable