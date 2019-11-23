package com.example.sharbat.presentation.fetures.events.all

import com.example.sharbat.presentation.fetures.events.EventsView

interface AllEventsView : EventsView {
    fun renderFilters()

    fun showProgress(show: Boolean)
}