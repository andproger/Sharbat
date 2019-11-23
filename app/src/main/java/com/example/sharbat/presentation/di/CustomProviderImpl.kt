package com.example.sharbat.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.sharbat.data.db.AppDatabase
import com.example.sharbat.data.db.repositories.RoomEventsRepository
import com.example.sharbat.data.network.ClientApiFactory
import com.example.sharbat.data.network.ClientApi
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.interactors.events.*
import io.reactivex.Observable

class CustomProviderImpl(
    context: Context
) : CustomProvider {

    private lateinit var appDatabase: AppDatabase
    private lateinit var clientApi: ClientApi

    private val eventsRepository by lazy { createEventsRepository() }

    init {
        setupAppDatabase(context)
        setupClientApi()
    }

    override fun provideAppDatabase() = appDatabase

    override fun provideClientApi() = clientApi

    override fun provideGetAllEventsInteractor(): GetEventsInteractor {
        return GetAllEventsInteractorImpl(
            eventsRepository
        )
    }

    override fun provideGetMyEventsInteractor(): GetEventsInteractor {
        return GetMyEventsInteractorImpl(
            eventsRepository
        )
    }

    override fun provideRefreshEventsInteractor(): RefreshEventsInteractor {
        return RefreshEventsInteractorImpl(
            clientApi, eventsRepository
        )
    }

    fun setupAppDatabase(context: Context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .build()
    }

    private fun setupClientApi() {
        clientApi = ClientApiFactory.createRestApi()
    }

    private fun createEventsRepository(): EventsRepository {
        return RoomEventsRepository(appDatabase.eventDao())
    }
}