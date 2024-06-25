package com.tony.nbctakehome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tony.nbctakehome.models.HomePageData
import com.tony.nbctakehome.repository.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val homePageRepository: HomePageRepository): ViewModel() {

    private val _homePageStateFlow = MutableStateFlow<HomePageUIState>(HomePageUIState.Loading)
    val homePageStateFlow = _homePageStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val homePage = homePageRepository.getHomePage().mapToState()
                _homePageStateFlow.value = HomePageUIState.Content(homePage);
            } catch (e: Exception) {
                _homePageStateFlow.value = HomePageUIState.Error(e.message)
            }
        }
    }

    private fun HomePageData.mapToState(): HomePageState {
        return HomePageState(
            shelves = this.shelves.mapIndexed { shelfId, shelf ->
                ShelfState(
                    id = shelfId,
                    shelfTitle = shelf.title,
                    type = shelf.type,
                    items = shelf.items.mapIndexed { itemId, item ->
                        ShelfItemState(
                            id = itemId,
                            title = item.title,
                            type = item.type,
                            imageUrl = item.image,
                            subtitle = item.subtitle,
                            labelBadge = item.labelBadge
                        )
                    }
                )
            }
        )
    }
}
