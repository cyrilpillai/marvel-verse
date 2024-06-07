package com.cyrilpillai.marvelverse.core.network.converter.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkResponseEnvelope<T>(
    @Json(name = "code") val code: String,
    @Json(name = "data") val data: NetworkData<T>
)

@JsonClass(generateAdapter = true)
data class NetworkData<T>(
    @Json(name = "offset") val offset: Int,
    @Json(name = "limit") val limit: Int,
    @Json(name = "total") val total: Int,
    @Json(name = "count") val count: Int,
    @Json(name = "results") val results: T
)