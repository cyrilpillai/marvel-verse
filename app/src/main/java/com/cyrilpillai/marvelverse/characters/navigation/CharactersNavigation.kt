package com.cyrilpillai.marvelverse.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cyrilpillai.marvelverse.characters.view.list.CharacterListRoute

const val CHARACTER_LIST_ROUTE = "character_list_route"

fun NavController.navigateToCharacterListScreen(
    navOptions: NavOptions
) {
    navigate(CHARACTER_LIST_ROUTE, navOptions)
}

fun NavGraphBuilder.characterListScreen(
    onCharacterClicked: (characterId: Int) -> Unit
) {
    composable(
        route = CHARACTER_LIST_ROUTE,
    ) {
        CharacterListRoute(onCharacterClicked)
    }
}