package com.cyrilpillai.marvelverse.core.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MarvelVerseNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        content = content
    )
}

@Composable
fun RowScope.MarvelNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    unselectedIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        label = label,
        icon = if (selected) selectedIcon else unselectedIcon,
        modifier = modifier
    )
}