package com.tony.nbctakehome.di

import com.tony.nbctakehome.repository.HomePageRepository
import com.tony.nbctakehome.repository.HomePageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomePageModule {
    @Binds
    abstract fun bindHomePageRepository(impl: HomePageRepositoryImpl): HomePageRepository
}