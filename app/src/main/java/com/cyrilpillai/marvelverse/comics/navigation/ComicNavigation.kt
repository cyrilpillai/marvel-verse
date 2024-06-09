package com.cyrilpillai.marvelverse.comics.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cyrilpillai.marvelverse.comics.view.list.ComicListRoute

const val COMIC_LIST_ROUTE = "comic_list_route"

fun NavController.navigateToComicListScreen(
    navOptions: NavOptions
) {
    navigate(COMIC_LIST_ROUTE, navOptions)
}

fun NavGraphBuilder.comicListScreen(
    onComicClicked: (comicId: Int) -> Unit
) {
    composable(
        route = COMIC_LIST_ROUTE,
    ) {
        ComicListRoute(onComicClicked)
    }
}