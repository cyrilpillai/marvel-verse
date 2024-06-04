package com.cyrilpillai.marvelverse.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cyrilpillai.marvelverse.characters.navigation.CHARACTERS_ROUTE
import com.cyrilpillai.marvelverse.characters.navigation.charactersScreen
import com.cyrilpillai.marvelverse.comics.navigation.comicsScreen
import com.cyrilpillai.marvelverse.series.navigation.seriesScreen

@Composable
fun MarvelVerseNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = CHARACTERS_ROUTE
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        charactersScreen {}
        comicsScreen {}
        seriesScreen {}
    }
}