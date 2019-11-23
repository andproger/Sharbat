package com.example.sharbat.presentation.fetures.events.all

import com.example.sharbat.presentation.fetures.events.EventsPresenter
import java.util.*

interface AllEventsPresenter : EventsPresenter<AllEventsView> {
    fun onRefresh()

    fun onFilterDateClicked()

    fun onFilterTypeClicked()

    fun onFilterPlaceClicked()

    fun onDateSelected(time: Date?)
}