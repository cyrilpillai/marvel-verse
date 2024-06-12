package com.cyrilpillai.marvelverse.characters.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.cyrilpillai.marvelverse.characters.data.local.entity.CharacterEntity
import com.cyrilpillai.marvelverse.characters.domain.CharacterRepo
import com.cyrilpillai.marvelverse.characters.view.list.model.CharacterItem
import com.cyrilpillai.marvelverse.characters.view.list.model.CharacterListUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    pager: Pager<Int, CharacterEntity>,
    private val characterRepo: CharacterRepo
) : ViewModel() {
    val characterItemFlow = pager.flow
        .map { pagingData ->
            pagingData.map { CharacterItem(it) }
        }
        .cachedIn(viewModelScope)

    fun onEvent(event: CharacterListUiEvent) {
    }
}