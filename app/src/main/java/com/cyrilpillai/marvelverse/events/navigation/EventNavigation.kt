package com.cyrilpillai.marvelverse.events.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cyrilpillai.marvelverse.events.view.list.EventListRoute

const val EVENT_LIST_ROUTE = "event_list_route"

fun NavController.navigateToEventListScreen(
    navOptions: NavOptions
) {
    navigate(EVENT_LIST_ROUTE, navOptions)
}

fun NavGraphBuilder.eventListScreen(
    onEventClicked: (eventId: Int) -> Unit
) {
    composable(
        route = EVENT_LIST_ROUTE,
    ) {
        EventListRoute(onEventClicked)
    }
}