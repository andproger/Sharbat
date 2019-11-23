package com.example.sharbat.presentation.fetures.events.all

import com.example.sharbat.presentation.fetures.events.EventsView
import java.util.*

interface AllEventsView : EventsView {

    fun showProgress(show: Boolean)

    fun showError()

    fun showDatePickerDialog(date: Date?)

    fun showTypePickerDialog()

    fun showPlacePickerDialog()

    fun showSelectedDate(date: Date?)
}