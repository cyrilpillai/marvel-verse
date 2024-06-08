package com.cyrilpillai.marvelverse.comics.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyrilpillai.marvelverse.comics.data.remote.model.ComicResource

@Entity(tableName = "comics")
data class ComicEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String,
) {
    constructor(comicResource: ComicResource) : this(
        id = comicResource.id,
        title = comicResource.title,
        description = comicResource.description ?: "",
        thumbnailUrl = comicResource.thumbnailResource.url
    )
}