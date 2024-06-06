package com.cyrilpillai.marvelverse.characters.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyrilpillai.marvelverse.characters.domain.CharactersRepo
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiEvent
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepo: CharactersRepo
) : ViewModel() {
    val uiState: StateFlow<CharactersUiState> = charactersRepo.fetchCharacters()
        .map { characters ->
            CharactersUiState.Success(characters)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CharactersUiState.Loading
        )

    init {
        viewModelScope.launch {
            charactersRepo.fetchCharacters()
        }
    }

    fun onEvent(event: CharactersUiEvent) {
    }
}