package com.tony.nbctakehome.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tony.nbctakehome.models.BadgeType
import com.tony.nbctakehome.ui.ShelfItemState
import com.tony.nbctakehome.ui.theme.StyleTheme

@Composable
private fun ShelfItem(
    item: ShelfItemState,
    modifier: Modifier = Modifier,
    content: @Composable (aspectRatio: Float) -> Unit
) {
    val aspectRatio = remember(item.imageUrl) { calculateAspectRatioFromUrl(item.imageUrl) }
    Column(
        modifier
            .width(calculateWidthFromAspectRatio(aspectRatio = aspectRatio))
    ) {
        content(aspectRatio)
    }
}

@Composable
private fun Badge(badgeType: BadgeType?, modifier: Modifier = Modifier) {
    when(badgeType) {
        BadgeType.NEW -> {
            Box(
                modifier = modifier
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    text = badgeType.name,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        BadgeType.LIVE -> {
            Box(
                modifier = modifier
                    .background(
                        color = Color.Red,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(4.dp)
            ) {
                Text(
                    text = badgeType.name,
                    color = Color.White,
                    style = StyleTheme.fontStyle.R1,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        BadgeType.EXPIRING -> {
            Box(
                modifier = modifier
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    text = badgeType.name,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        else -> {}
    }
}

@Composable
fun ShowItem(item: ShelfItemState, modifier: Modifier = Modifier) {
    ShelfItem(item = item, modifier = modifier) { aspectRatio ->
        NBCImage(imageUrl = item.imageUrl, aspectRatio)
        Text(
            text = item.title,
            style = StyleTheme.fontStyle.R2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun EpisodeItem(item: ShelfItemState, modifier: Modifier = Modifier) {
    ShelfItem(item = item, modifier = modifier) { aspectRatio ->
        Box {
            NBCImage(imageUrl = item.imageUrl, aspectRatio)
            Badge(badgeType = item.labelBadge, modifier = Modifier.align(Alignment.TopStart))
        }
        Text(
            text = item.title,
            style = StyleTheme.fontStyle.R2,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        item.subtitle?.let {
            Text(
                text = item.subtitle,
                style = StyleTheme.fontStyle.R1,
                color = StyleTheme.colors.subText
            )
        }
    }
}

@Composable
fun LiveItem(item: ShelfItemState, modifier: Modifier = Modifier) {
    ShelfItem(item = item, modifier = modifier) { aspectRatio ->
        Box {
            NBCImage(imageUrl = item.imageUrl, aspectRatio)
            Badge(
                badgeType = item.labelBadge,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 8.dp, bottom = 16.dp)
            )
            LinearProgressIndicator(
                progress = 0.5f,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(6.dp)
            )
        }
        Text(
            text = item.title,
            style = StyleTheme.fontStyle.R2,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        item.subtitle?.let {
            Text(
                text = item.subtitle,
                style = StyleTheme.fontStyle.R1,
                color = StyleTheme.colors.subText
            )
        }
    }
}