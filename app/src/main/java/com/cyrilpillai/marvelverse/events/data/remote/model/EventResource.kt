package com.cyrilpillai.marvelverse.events.data.remote.model

import com.cyrilpillai.marvelverse.core.network.model.ThumbnailResource
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventResource(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnailResource: ThumbnailResource
)