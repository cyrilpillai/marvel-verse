package com.cyrilpillai.marvelverse.events.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyrilpillai.marvelverse.events.domain.EventRepo
import com.cyrilpillai.marvelverse.events.view.list.model.EventItem
import com.cyrilpillai.marvelverse.events.view.list.model.EventListUiEvent
import com.cyrilpillai.marvelverse.events.view.list.model.EventListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel() {
    val uiState: StateFlow<EventListUiState> = eventRepo.getAllEvents()
        .map { events ->
            EventListUiState.Success(
                events.map { EventItem(it) }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = EventListUiState.Loading
        )

    init {
        viewModelScope.launch {
            if (eventRepo.getEventsCount() < 1) {
                eventRepo.fetchEvents()
            }
        }
    }

    fun onEvent(event: EventListUiEvent) {
    }
}