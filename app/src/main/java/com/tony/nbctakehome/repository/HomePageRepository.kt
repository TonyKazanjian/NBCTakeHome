package com.tony.nbctakehome.repository

import android.content.Context
import com.tony.nbctakehome.di.IoDispatcher
import com.tony.nbctakehome.models.HomePageData
import com.tony.nbctakehome.ui.HomePageState
import com.tony.nbctakehome.ui.ShelfItemState
import com.tony.nbctakehome.ui.ShelfState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface HomePageRepository {
    suspend fun getHomePage(fileName: String = "homepage.json"): HomePageData
}

class HomePageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : HomePageRepository {
    override suspend fun getHomePage(fileName: String): HomePageData {
        return withContext(dispatcher) {
            val homepageJson =
                context.assets.open(fileName).bufferedReader().use { it.readText() }
            if (homepageJson.isEmpty()) {
                throw IllegalStateException("$fileName not found or empty")
            }
            Json.decodeFromString<HomePageData>(homepageJson)
        }
    }
}