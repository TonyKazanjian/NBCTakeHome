package com.tony.nbctakehome.ui

import androidx.compose.runtime.Immutable
import com.tony.nbctakehome.models.BadgeType
import com.tony.nbctakehome.models.ContentType

@Immutable
sealed class HomePageUIState {
    data object Loading : HomePageUIState()
    data class Content(val homePage: HomePageState) : HomePageUIState()
    data class Error(val message: String?) : HomePageUIState()
}

@Immutable
data class HomePageState(
    val shelves: List<ShelfState> = emptyList()
)

@Immutable
data class ShelfState(
    val id: Int,
    val shelfTitle: String,
    val type: String,
    val items: List<ShelfItemState>
)

@Immutable
data class ShelfItemState(
    val id: Int,
    val title: String,
    val type: ContentType,
    val imageUrl: String,
    val subtitle: String? = null,
    val labelBadge: BadgeType? = null,
)