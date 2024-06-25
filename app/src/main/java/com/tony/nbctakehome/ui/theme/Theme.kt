package com.tony.nbctakehome.ui.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

interface FontStyle {
    val R1: TextStyle
    val R2: TextStyle
    val R3: TextStyle
}

data class NBCStyleColors(
    val text: Color = Color(0xFFFFFFFF),
    val titleText: Color = Color(0xDCFFFFFF),
    val subText: Color = Color(0xBFFFFFFF),
    val backgroundGradientTop: Color = Color(0xFF0089A8),
    val backgroundGradientBottom: Color = Color(0xFF500053)
)

val defaultStyleColors = NBCStyleColors();

val defaultFontSizes = listOf(14.sp, 16.sp, 24.sp)

val defaultFontStyle = NBCFontStyle(
    fontColor = defaultStyleColors.text,
    fontSizes = defaultFontSizes
)

data class NBCFontStyle(
    private val fontColor: Color,
    private val fontFamily: FontFamily = FontFamily.SansSerif,
    private val fontSizes: List<TextUnit> = defaultFontSizes
) : FontStyle {
    private val regularBase = TextStyle(
        fontWeight = FontWeight.Normal,
        color = fontColor,
        fontFamily = fontFamily
    )
    override val R1 by lazy { regularBase.copy(fontSize = fontSizes[0]) }
    override val R2 by lazy { regularBase.copy(fontSize = fontSizes[1]) }
    override val R3 by lazy { regularBase.copy(fontSize = fontSizes[2]) }
}

val LocalColorStyle = compositionLocalOf { defaultStyleColors }
val LocalFontStyle = compositionLocalOf { defaultFontStyle }

@Composable
fun NBCTakeHomeTheme(
    colors: NBCStyleColors = defaultStyleColors,
    fontStyle: NBCFontStyle = defaultFontStyle,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorStyle provides colors,
        LocalFontStyle provides fontStyle
    ) {
        MaterialTheme {
            content()
        }
    }
}

object StyleTheme {
    val colors: NBCStyleColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorStyle.current

    val fontStyle: NBCFontStyle
        @Composable
        @ReadOnlyComposable
        get() = LocalFontStyle.current
}