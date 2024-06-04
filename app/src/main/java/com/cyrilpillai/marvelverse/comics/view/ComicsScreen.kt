package com.cyrilpillai.marvelverse.comics.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComicsRoute(
    onComicClicked: (comicId: String) -> Unit,
) {
    ComicsScreen(
        onComicClicked
    )
}

@Composable
fun ComicsScreen(
    onComicClicked: (comicId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "Marvel Comics")
    }
}


@Preview
@Composable
fun ComicsScreenPreview() {
    ComicsScreen(
        onComicClicked = {}
    )
}