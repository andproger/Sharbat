package com.example.sharbat.presentation.features.events

interface EventsView {

    fun showEvents(events: List<EventViewState>)

    fun openEvent(id: String)
}