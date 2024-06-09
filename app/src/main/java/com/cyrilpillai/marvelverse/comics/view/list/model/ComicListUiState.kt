package com.cyrilpillai.marvelverse.comics.view.list.model

import com.cyrilpillai.marvelverse.comics.data.local.entity.ComicEntity

sealed class ComicListUiState {
    data object Loading : ComicListUiState()

    data class Success(
        val comics: List<ComicItem>
    ) : ComicListUiState()

    data class Failure(
        val errorMessage: String
    ) : ComicListUiState()
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