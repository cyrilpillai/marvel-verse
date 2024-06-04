package com.cyrilpillai.marvelverse.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.cyrilpillai.marvelverse.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int
) {
    CHARACTERS(
        selectedIcon = Icons.Default.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        iconTextId = R.string.characters_title,
        titleTextId = R.string.characters_title
    ),
    COMICS(
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
        iconTextId = R.string.comics_title,
        titleTextId = R.string.comics_title
    ),
    SERIES(
        selectedIcon = Icons.Default.Menu,
        unselectedIcon = Icons.Outlined.Menu,
        iconTextId = R.string.series_title,
        titleTextId = R.string.series_title
    ),
}
