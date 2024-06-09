package com.cyrilpillai.marvelverse.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.cyrilpillai.marvelverse.R
import com.cyrilpillai.marvelverse.characters.navigation.CHARACTER_LIST_ROUTE
import com.cyrilpillai.marvelverse.comics.navigation.COMIC_LIST_ROUTE
import com.cyrilpillai.marvelverse.events.navigation.EVENT_LIST_ROUTE

enum class TopLevelDestination(
    val id: String,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
) {
    CHARACTERS(
        id = CHARACTER_LIST_ROUTE,
        iconTextId = R.string.characters_title,
        titleTextId = R.string.characters_title,
        selectedIcon = R.drawable.characters_nav_icon_selected,
        unselectedIcon = R.drawable.characters_nav_icon_unselected,
    ),
    COMICS(
        id = COMIC_LIST_ROUTE,
        iconTextId = R.string.comics_title,
        titleTextId = R.string.comics_title,
        selectedIcon = R.drawable.comics_nav_icon_selected,
        unselectedIcon = R.drawable.comics_nav_icon_unselected,
    ),
    EVENTS(
        id = EVENT_LIST_ROUTE,
        iconTextId = R.string.events_title,
        titleTextId = R.string.events_title,
        selectedIcon = R.drawable.events_nav_icon_selected,
        unselectedIcon = R.drawable.events_nav_icon_unselected,
    ),
}
