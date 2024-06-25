package com.tony.nbctakehome.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.util.regex.Pattern

@Composable
fun NBCImage(imageUrl: String, aspectRatio: Float) {
    Box(modifier = Modifier.aspectRatio(aspectRatio)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
    }
}

fun calculateAspectRatioFromUrl(url: String): Float {
    val pattern = Pattern.compile("(\\d+)[x_](\\d+)")
    val matcher = pattern.matcher(url)
    while (matcher.find()) {
        val width = matcher.group(1)?.toFloat() ?: 0f
        val height = matcher.group(2)?.toFloat() ?: 0f
        if (isValidDimension(width) && isValidDimension(height)) {
            return width / height
        }
    }
    return 16f / 9f
}

private fun isValidDimension(value: Float): Boolean {
    return value in 100f..10000f
}

@Composable
fun calculateWidthFromAspectRatio(aspectRatio: Float): Dp {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val orientation = remember (configuration) { configuration.orientation }
    val screenWidthDp = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val widthPercentage = when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 0.5f
        else -> 1f
    }
    return when (aspectRatio) {
        16f / 9f -> (screenWidthDp / 1.5 * widthPercentage).dp
        3f / 4f -> (screenWidthDp / 2.5 * widthPercentage).dp
        else -> 0.dp
    }
}
