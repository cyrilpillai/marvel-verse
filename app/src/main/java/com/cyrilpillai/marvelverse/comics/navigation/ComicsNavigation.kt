package com.cyrilpillai.marvelverse.comics.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cyrilpillai.marvelverse.comics.view.ComicsRoute

const val COMICS_ROUTE = "comics_route"

fun NavController.navigateToComicsScreen(
    navOptions: NavOptions
) {
    navigate(COMICS_ROUTE, navOptions)
}

fun NavGraphBuilder.comicsScreen(
    onComicClicked: (comicId: Int) -> Unit
) {
    composable(
        route = COMICS_ROUTE,
    ) {
        ComicsRoute(onComicClicked)
    }
}