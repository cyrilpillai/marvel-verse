package com.cyrilpillai.marvelverse.series.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cyrilpillai.marvelverse.series.view.SeriesRoute

const val SERIES_ROUTE = "series_route"

fun NavController.navigateToSeriesScreen(
    navOptions: NavOptions
) {
    navigate(SERIES_ROUTE, navOptions)
}

fun NavGraphBuilder.seriesScreen(
    onComicClicked: (comicId: String) -> Unit
) {
    composable(
        route = SERIES_ROUTE,
    ) {
        SeriesRoute(onComicClicked)
    }
}