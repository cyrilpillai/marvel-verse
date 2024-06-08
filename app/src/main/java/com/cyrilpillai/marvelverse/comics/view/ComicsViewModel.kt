package com.cyrilpillai.marvelverse.comics.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyrilpillai.marvelverse.comics.domain.ComicRepo
import com.cyrilpillai.marvelverse.comics.view.model.ComicItem
import com.cyrilpillai.marvelverse.comics.view.model.ComicsUiEvent
import com.cyrilpillai.marvelverse.comics.view.model.ComicsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val comicRepo: ComicRepo
) : ViewModel() {
    val uiState: StateFlow<ComicsUiState> = comicRepo.getAllComics()
        .map { comics ->
            ComicsUiState.Success(
                comics.map { ComicItem(it) }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ComicsUiState.Loading
        )

    init {
        viewModelScope.launch {
            if (comicRepo.getComicsCount() < 1) {
                comicRepo.fetchComics()
            }
        }
    }

    fun onEvent(event: ComicsUiEvent) {
    }
}