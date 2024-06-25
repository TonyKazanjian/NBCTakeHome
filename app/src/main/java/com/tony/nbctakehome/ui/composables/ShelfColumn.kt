package com.tony.nbctakehome.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tony.nbctakehome.ui.HomePageState

@Composable
fun ShelfColumn(
    homePageState: HomePageState,
    modifier: Modifier = Modifier,
    ) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(
            items = homePageState.shelves,
            key = { it.id },
            contentType = { it.type }
        ) {
            ShelfRow(shelfState = it)
        }
    }

}