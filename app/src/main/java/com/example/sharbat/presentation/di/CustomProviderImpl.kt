package com.example.sharbat.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.sharbat.data.db.AppDatabase
import com.example.sharbat.data.network.ClientApiFactory
import com.example.sharbat.data.network.ClientApi

class CustomProviderImpl(
    context: Context
) : CustomProvider {

    private lateinit var appDatabase: AppDatabase
    private lateinit var clientApi: ClientApi

    init {
        setupAppDatabase(context)
        setupClientApi()
    }

    override fun provideAppDatabase() = appDatabase

    override fun provideClientApi() = clientApi

    private fun setupAppDatabase(context: Context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .build()
    }

    private fun setupClientApi() {
        clientApi = ClientApiFactory.createRestApi()
    }
}