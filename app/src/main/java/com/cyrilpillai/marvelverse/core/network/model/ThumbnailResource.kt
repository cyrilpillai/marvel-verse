package com.cyrilpillai.marvelverse.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailResource(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
) {
    val url = "${path.replace("http", "https")}.$extension"
}