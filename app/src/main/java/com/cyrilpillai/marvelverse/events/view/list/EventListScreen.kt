package com.cyrilpillai.marvelverse.events.view.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cyrilpillai.marvelverse.R
import com.cyrilpillai.marvelverse.events.view.list.model.EventItem
import com.cyrilpillai.marvelverse.events.view.list.model.EventListUiEvent
import com.cyrilpillai.marvelverse.events.view.list.model.EventListUiState
import com.cyrilpillai.marvelverse.ui.theme.Red100

@Composable
fun EventListRoute(
    onEventClicked: (eventId: Int) -> Unit,
    viewModel: EventListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    EventListScreen(
        state,
        viewModel::onEvent,
        onEventClicked
    )
}

@Composable
fun EventListScreen(
    state: EventListUiState,
    onEvent: (event: EventListUiEvent) -> Unit,
    onEventClicked: (eventId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Red100,
                    )
                )
            )
    ) {
        when (state) {
            is EventListUiState.Loading -> {
                CircularProgressIndicator()
            }

            is EventListUiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.events) { EventView(it, onEventClicked) }
                }
            }

            else -> Unit
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventView(
    eventItem: EventItem,
    onEventClicked: (eventId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        onClick = { onEventClicked(eventItem.id) },
        modifier = modifier
    ) {
        AsyncImage(
            model = eventItem.thumbnailUrl,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "event image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
        )
        Text(
            text = eventItem.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        )
        Text(
            text = eventItem.description,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(
                    top = 4.dp,
                    bottom = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        )
    }
}


@Preview
@Composable
fun EventListScreenPreview() {
    EventListScreen(
        state = EventListUiState.Success(
            events = listOf(
                EventItem(
                    id = 301,
                    title = "All-New All-Different Marvel",
                    description = "This fall, prepare for an all-new, all-different Marvel Universe to begin!",
                    thumbnailUrl = "http://i.annihil.us/u/prod/marvel/i/mg/b/f0/55bfa91b34ac4.jpg"
                )
            )
        ),
        onEvent = {},
        onEventClicked = {}
    )
}