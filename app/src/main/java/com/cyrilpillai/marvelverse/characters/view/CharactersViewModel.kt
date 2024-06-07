package com.cyrilpillai.marvelverse.characters.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyrilpillai.marvelverse.characters.domain.CharactersRepo
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiEvent
import com.cyrilpillai.marvelverse.characters.view.model.CharactersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepo: CharactersRepo
) : ViewModel() {
    /*val uiState: StateFlow<CharactersUiState> = charactersRepo.fetchCharacters()
        .map { characters ->
            CharactersUiState.Success(characters)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CharactersUiState.Loading
        )*/
    private val _uiState: MutableStateFlow<CharactersUiState> =
        MutableStateFlow(CharactersUiState.Loading)
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            charactersRepo.fetchCharacters().collect {
                _uiState.value = CharactersUiState.Success(it)
            }
        }
    }

    fun onEvent(event: CharactersUiEvent) {
    }
}