package com.example.sharbat.presentation.di

import com.example.sharbat.data.db.AppDatabase
import com.example.sharbat.data.network.ClientApi

interface CustomProvider {
    fun provideAppDatabase() : AppDatabase

    fun provideClientApi(): ClientApi
}