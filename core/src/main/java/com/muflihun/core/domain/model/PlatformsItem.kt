package com.muflihun.core.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PlatformsItem(
	@field:SerializedName("platform")
	val platform: Platform? = null
) : Parcelable