package com.tony.nbctakehome

import com.tony.nbctakehome.models.BadgeType
import com.tony.nbctakehome.models.ContentType
import com.tony.nbctakehome.models.HomePageData
import com.tony.nbctakehome.repository.HomePageRepository
import com.tony.nbctakehome.ui.HomePageState
import com.tony.nbctakehome.ui.HomePageUIState
import com.tony.nbctakehome.ui.HomePageViewModel
import com.tony.nbctakehome.ui.ShelfItemState
import com.tony.nbctakehome.ui.ShelfState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Rule
import org.junit.Test

class HomePageViewModelTest {

    private val repository = FakeHomePageRepository()

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `data maps to ui state`() = runTest {
        val viewModel = HomePageViewModel(repository)
        val expectedState = HomePageUIState.Content(
            HomePageState(
                shelves = listOf(
                    ShelfState(
                        id = 0,
                        shelfTitle = "Continue Watching",
                        type = "Shelf",
                        items = listOf(
                            ShelfItemState(
                                id = 0,
                                title = "Hallie Jackson NOW",
                                type = ContentType.Live,
                                subtitle = "Live News Streaming",
                                imageUrl= "https://img.nbc.com/sites/nbcunbc/files/images/2021/2/04/NBC-News_2048_1152.jpg",
                                labelBadge = BadgeType.LIVE,
                            )
                        )
                    )
                )
            )
        )
        assertEquals(expectedState, viewModel.homePageStateFlow.value)
    }

    @Test
    fun `catches error from missing or invalid file`() = runTest {
        repository.homePageJsonString = "invalid json"
        val viewModel = HomePageViewModel(repository)

        assert(viewModel.homePageStateFlow.value is HomePageUIState.Error)

    }
}

private class FakeHomePageRepository: HomePageRepository {
    var homePageJsonString = """
        {
          "page": "HOMEPAGE",
          "shelves": [
            {
              "title": "Continue Watching",
              "type": "Shelf",
              "items": [
                {
                  "type": "Live",
                  "tagline": "WATCH NBC NEWS NOW LIVE",
                  "title": "Hallie Jackson NOW",
                  "subtitle": "Live News Streaming",
                  "labelBadge": "LIVE",
                  "image": "https://img.nbc.com/sites/nbcunbc/files/images/2021/2/04/NBC-News_2048_1152.jpg"
                }
              ]
            }
          ]
        }
    """.trimIndent()

    override suspend fun getHomePage(fileName: String): HomePageData {
        return Json.decodeFromString(homePageJsonString)
    }
}