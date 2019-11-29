package com.example.sharbat.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.sharbat.data.db.AppDatabase
import com.example.sharbat.data.db.repositories.RoomEventsRepository
import com.example.sharbat.data.db.repositories.RoomFavoriteEventsRepository
import com.example.sharbat.data.network.ClientApiFactory
import com.example.sharbat.data.network.ClientApi
import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.gateways.FavoriteEventsRepository
import com.example.sharbat.domain.interactors.events.*
import com.example.sharbat.domain.interactors.events.favorite.MarkAsFavoriteInteractor
import com.example.sharbat.domain.interactors.events.favorite.MarkAsFavoriteInteractorImpl

class CustomProviderImpl(
    context: Context
) : CustomProvider {

    private lateinit var appDatabase: AppDatabase
    private lateinit var clientApi: ClientApi

    private val eventsRepository by lazy { createEventsRepository() }
    private val favoriteEventsRepository by lazy { createFavoriteEventsRepository() }

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
            eventsRepository,
            favoriteEventsRepository
        )
    }

    override fun provideRefreshEventsInteractor(): RefreshEventsInteractor {
        return RefreshEventsInteractorImpl(
            clientApi, eventsRepository
        )
    }

    override fun providerGetEventInteractor(): GetEventInteractor {
        return GetEventInteractorImpl(eventsRepository, favoriteEventsRepository)
    }

    override fun provideMarkAsFavoriteInteractor(): MarkAsFavoriteInteractor {
        return MarkAsFavoriteInteractorImpl(
            favoriteEventsRepository
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

    private fun createFavoriteEventsRepository(): FavoriteEventsRepository {
        return RoomFavoriteEventsRepository(appDatabase.favoritEventDao())
    }
}