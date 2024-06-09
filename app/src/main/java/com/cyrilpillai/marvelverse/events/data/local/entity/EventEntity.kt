package com.cyrilpillai.marvelverse.events.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyrilpillai.marvelverse.comics.data.remote.model.ComicResource
import com.cyrilpillai.marvelverse.events.data.remote.model.EventResource

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String,
) {
    constructor(eventResource: EventResource) : this(
        id = eventResource.id,
        title = eventResource.title,
        description = eventResource.description,
        thumbnailUrl = eventResource.thumbnailResource.url
    )
}