package com.example.sharbat.presentation.di

import com.example.sharbat.data.db.AppDatabase
import com.example.sharbat.data.network.ClientApi
import com.example.sharbat.domain.interactors.events.GetEventInteractor
import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import com.example.sharbat.domain.interactors.events.favorite.MarkAsFavoriteInteractor
import com.example.sharbat.domain.interactors.events.RefreshEventsInteractor

interface CustomProvider {
    fun provideAppDatabase(): AppDatabase

    fun provideClientApi(): ClientApi

    fun provideGetAllEventsInteractor(): GetEventsInteractor

    fun provideGetMyEventsInteractor(): GetEventsInteractor

    fun provideRefreshEventsInteractor(): RefreshEventsInteractor

    fun providerGetEventInteractor(): GetEventInteractor

    fun provideMarkAsFavoriteInteractor(): MarkAsFavoriteInteractor
}