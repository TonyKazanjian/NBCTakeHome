package com.tony.nbctakehome.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tony.nbctakehome.models.ContentType
import com.tony.nbctakehome.ui.ShelfState
import com.tony.nbctakehome.ui.theme.StyleTheme

@Composable
fun ShelfRow(
    shelfState: ShelfState,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = shelfState.shelfTitle,
            style = StyleTheme.fontStyle.R3,
            modifier = Modifier.padding(bottom = 8.dp, start = 12.dp)
        )
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(
                items = shelfState.items,
                key = { it.id },
                contentType = { it.type }
            ) {
                when(it.type) {
                    ContentType.Show -> ShowItem(item = it)
                    ContentType.Episode -> EpisodeItem(item = it)
                    ContentType.Live -> LiveItem(item = it)
                }
            }
        }
    }
}