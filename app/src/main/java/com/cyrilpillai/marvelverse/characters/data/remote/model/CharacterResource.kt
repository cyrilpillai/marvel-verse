package com.cyrilpillai.marvelverse.characters.data.remote.model

import com.cyrilpillai.marvelverse.core.network.model.ThumbnailResource
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResource(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnailResource: ThumbnailResource
)