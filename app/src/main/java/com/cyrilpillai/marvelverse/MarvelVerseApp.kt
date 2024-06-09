package com.cyrilpillai.marvelverse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.cyrilpillai.marvelverse.characters.navigation.navigateToCharacterListScreen
import com.cyrilpillai.marvelverse.comics.navigation.navigateToComicListScreen
import com.cyrilpillai.marvelverse.core.navigation.MarvelNavigationBarItem
import com.cyrilpillai.marvelverse.core.navigation.MarvelVerseNavHost
import com.cyrilpillai.marvelverse.core.navigation.MarvelVerseNavigationBar
import com.cyrilpillai.marvelverse.core.navigation.TopLevelDestination
import com.cyrilpillai.marvelverse.events.navigation.navigateToEventListScreen
import com.cyrilpillai.marvelverse.ui.theme.MarvelVerseTheme

@Composable
fun MarvelVerseApp() {
    val navController = rememberNavController()
    MarvelVerseTheme {
        Scaffold(
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                MarvelVerseNavigationBar {
                    TopLevelDestination.entries.forEach { destination ->
                        MarvelNavigationBarItem(
                            selected = currentDestination.isTopLevelDestinationInHierarchy(
                                destination
                            ),
                            onClick = { onNavigationBarItemClicked(navController, destination) },
                            label = { Text(stringResource(destination.iconTextId)) },
                            selectedIcon = {
                                Icon(
                                    painter = painterResource(id = destination.selectedIcon),
                                    "nav selected icon"
                                )
                            },
                            unselectedIcon = {
                                Icon(
                                    painter = painterResource(id = destination.unselectedIcon),
                                    "nav unselected icon"
                                )
                            }
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            MarvelVerseNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

private fun onNavigationBarItemClicked(
    navController: NavController,
    topLevelDestination: TopLevelDestination
) {
    val topLevelNavOptions = navOptions {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true
    }
    when (topLevelDestination) {
        TopLevelDestination.CHARACTERS -> navController.navigateToCharacterListScreen(
            topLevelNavOptions
        )

        TopLevelDestination.COMICS -> navController.navigateToComicListScreen(
            topLevelNavOptions
        )

        TopLevelDestination.EVENTS -> navController.navigateToEventListScreen(
            topLevelNavOptions
        )
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.id, true) ?: false
    } ?: false

@Preview(showBackground = true)
@Composable
fun MarvelVerseAppPreview() {
    MarvelVerseApp()
}