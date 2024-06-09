package com.cyrilpillai.marvelverse.events.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EventListRoute(
    onEventClicked: (eventId: Int) -> Unit,
) {
    EventListScreen(
        onEventClicked
    )
}

@Composable
fun EventListScreen(
    onEventClicked: (eventId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "Marvel Events")
    }
}


@Preview
@Composable
fun EventListScreenPreview() {
    EventListScreen(
        onEventClicked = {}
    )
}