package com.tony.nbctakehome.models

import kotlinx.serialization.Serializable

@Serializable
data class HomePageData(
    val page: String,
    val shelves: List<Shelf>
)

@Serializable
data class Shelf(
    val title: String,
    val type: String,
    val items: List<Item>
)

@Serializable
data class Item(
    val type: ContentType,
    val title: String,
    val subtitle: String? = null,
    val tagline: String? = null,
    val image: String,
    val labelBadge: BadgeType? = null
)

enum class ContentType {
    Show,
    Episode,
    Live,
}

enum class BadgeType {
    NEW,
    LIVE,
    EXPIRING
}