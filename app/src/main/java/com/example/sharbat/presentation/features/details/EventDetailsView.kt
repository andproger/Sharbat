package com.example.sharbat.presentation.features.details

import com.example.sharbat.domain.entities.Event

interface EventDetailsView {
    fun renderStar(show: Boolean)

    fun renderContent(event: Event)

    fun register(link: String?)

}