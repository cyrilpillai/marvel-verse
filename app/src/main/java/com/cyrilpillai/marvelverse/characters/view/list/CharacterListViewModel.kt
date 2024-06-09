package com.cyrilpillai.marvelverse.characters.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyrilpillai.marvelverse.characters.domain.CharacterRepo
import com.cyrilpillai.marvelverse.characters.view.list.model.CharacterItem
import com.cyrilpillai.marvelverse.characters.view.list.model.CharacterListUiEvent
import com.cyrilpillai.marvelverse.characters.view.list.model.CharacterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterRepo: CharacterRepo
) : ViewModel() {
    val uiState: StateFlow<CharacterListUiState> = characterRepo.getAllCharacters()
        .map { characters ->
            CharacterListUiState.Success(
                characters.map { CharacterItem(it) }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CharacterListUiState.Loading
        )

    init {
        viewModelScope.launch {
            if (characterRepo.getCharactersCount() < 1) {
                characterRepo.fetchCharacters()
            }
        }
    }

    fun onEvent(event: CharacterListUiEvent) {
    }
}