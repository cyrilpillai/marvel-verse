package com.cyrilpillai.marvelverse.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cyrilpillai.marvelverse.characters.navigation.CHARACTER_LIST_ROUTE
import com.cyrilpillai.marvelverse.characters.navigation.characterListScreen
import com.cyrilpillai.marvelverse.comics.navigation.comicListScreen
import com.cyrilpillai.marvelverse.events.navigation.eventListScreen

@Composable
fun MarvelVerseNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = CHARACTER_LIST_ROUTE
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        characterListScreen {}
        comicListScreen {}
        eventListScreen {}
    }
}