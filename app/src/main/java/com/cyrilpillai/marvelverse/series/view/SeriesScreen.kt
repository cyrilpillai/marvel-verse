package com.cyrilpillai.marvelverse.series.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SeriesRoute(
    onSeriesClicked: (seriesId: String) -> Unit,
) {
    SeriesScreen(
        onSeriesClicked
    )
}

@Composable
fun SeriesScreen(
    onSeriesClicked: (seriesId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "Marvel Series")
    }
}


@Preview
@Composable
fun SeriesScreenPreview() {
    SeriesScreen(
        onSeriesClicked = {}
    )
}