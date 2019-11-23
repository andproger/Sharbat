package com.example.sharbat.presentation.fetures.events.all

import com.example.sharbat.presentation.fetures.events.EventsPresenter

interface AllEventsPresenter : EventsPresenter<AllEventsView> {
    fun onRefresh()
}