package com.cyrilpillai.marvelverse.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cyrilpillai.marvelverse.characters.view.CharactersRoute

const val CHARACTERS_ROUTE = "characters_route"

fun NavController.navigateToCharactersScreen(
    navOptions: NavOptions
) {
    navigate(CHARACTERS_ROUTE, navOptions)
}

fun NavGraphBuilder.charactersScreen(
    onCharacterClicked: (characterId: Int) -> Unit
) {
    composable(
        route = CHARACTERS_ROUTE,
    ) {
        CharactersRoute(onCharacterClicked)
    }
}