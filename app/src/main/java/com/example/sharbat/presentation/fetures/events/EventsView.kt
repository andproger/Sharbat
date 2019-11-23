package com.example.sharbat.presentation.fetures.events

interface EventsView {

    fun showEvents(events: List<EventViewState>)

    fun openEvent(id: String)
}