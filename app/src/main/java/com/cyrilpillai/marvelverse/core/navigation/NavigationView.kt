package com.cyrilpillai.marvelverse.core.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cyrilpillai.marvelverse.ui.theme.Red100
import com.cyrilpillai.marvelverse.ui.theme.Red700
import com.cyrilpillai.marvelverse.ui.theme.Red900

@Composable
fun MarvelVerseNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        content = content,
        containerColor = Red700
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
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = Red100,
            selectedTextColor = Color.White,
            unselectedTextColor = Red100,
            indicatorColor = Red900
        ),
        selected = selected,
        onClick = onClick,
        label = label,
        icon = if (selected) selectedIcon else unselectedIcon,
        modifier = modifier
    )
}