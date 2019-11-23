package com.example.sharbat.presentation.fetures.events

interface EventsPresenter<V : EventsView> {

    fun attachView(view: V)

    fun onItemClicked(id: String)

    fun onDestroy()
}