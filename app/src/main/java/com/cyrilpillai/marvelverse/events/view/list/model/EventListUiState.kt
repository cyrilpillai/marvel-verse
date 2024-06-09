package com.cyrilpillai.marvelverse.events.view.list.model

import com.cyrilpillai.marvelverse.events.data.local.entity.EventEntity

sealed class EventListUiState {
    data object Loading : EventListUiState()

    data class Success(
        val events: List<EventItem>
    ) : EventListUiState()

    data class Failure(
        val errorMessage: String
    ) : EventListUiState()
}

data class EventItem(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String
) {
    constructor(eventEntity: EventEntity) : this(
        id = eventEntity.id,
        title = eventEntity.title,
        description = eventEntity.description,
        thumbnailUrl = eventEntity.thumbnailUrl
    )
}