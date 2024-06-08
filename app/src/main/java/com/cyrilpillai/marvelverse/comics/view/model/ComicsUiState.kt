package com.cyrilpillai.marvelverse.comics.view.model

import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity

sealed class ComicsUiState {
    data object Loading : ComicsUiState()

    data class Success(
        val comics: List<ComicItem>
    ) : ComicsUiState()

    data class Failure(
        val errorMessage: String
    ) : ComicsUiState()
}

data class ComicItem(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String
) {
    constructor(comicEntity: ComicEntity) : this(
        id = comicEntity.id,
        title = comicEntity.title,
        description = comicEntity.description,
        thumbnailUrl = comicEntity.thumbnailUrl
    )
}