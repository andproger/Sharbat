package com.example.sharbat.presentation.di

import com.example.sharbat.data.db.AppDatabase
import com.example.sharbat.data.network.ClientApi
import com.example.sharbat.domain.interactors.events.GetEventsInteractor

interface CustomProvider {
    fun provideAppDatabase(): AppDatabase

    fun provideClientApi(): ClientApi

    fun provideGetAllEventsInteractor(): GetEventsInteractor

    fun provideGetMyEventsInteractor(): GetEventsInteractor
}