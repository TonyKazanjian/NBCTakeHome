package com.tony.nbctakehome.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.tony.nbctakehome.ui.composables.ShelfColumn
import com.tony.nbctakehome.ui.theme.NBCTakeHomeTheme
import com.tony.nbctakehome.ui.theme.StyleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomePageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NBCTakeHomeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val homePageUiState = homeViewModel.homePageStateFlow.collectAsState()
                    when (homePageUiState.value) {
                        HomePageUIState.Loading -> {
                            //Show a loading spinner
                        }

                        is HomePageUIState.Content -> {
                            HomePage(
                                state = homePageUiState.value as HomePageUIState.Content,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                StyleTheme.colors.backgroundGradientTop,
                                                StyleTheme.colors.backgroundGradientBottom
                                            )
                                        )
                                    )
                                    .padding(top = innerPadding.calculateTopPadding())
                            )
                        }
                        is HomePageUIState.Error -> {
                            //Show an error message
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomePage(state: HomePageUIState.Content, modifier: Modifier) {
    Box(modifier) {
        ShelfColumn(
            homePageState = state.homePage
        )
    }
}