package com.cyrilpillai.marvelverse.comics.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyrilpillai.marvelverse.comics.domain.ComicRepo
import com.cyrilpillai.marvelverse.comics.view.list.model.ComicItem
import com.cyrilpillai.marvelverse.comics.view.list.model.ComicListUiEvent
import com.cyrilpillai.marvelverse.comics.view.list.model.ComicListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicListViewModel @Inject constructor(
    private val comicRepo: ComicRepo
) : ViewModel() {
    val uiState: StateFlow<ComicListUiState> = comicRepo.getAllComics()
        .map { comics ->
            ComicListUiState.Success(
                comics.map { ComicItem(it) }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ComicListUiState.Loading
        )

    init {
        viewModelScope.launch {
            if (comicRepo.getComicsCount() < 1) {
                comicRepo.fetchComics()
            }
        }
    }

    fun onEvent(event: ComicListUiEvent) {
    }
}