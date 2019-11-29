package com.example.sharbat.presentation.features.details

interface EventDetailsPresenter {
    fun attachView(view: EventDetailsView)

    fun onStarClicked()

    fun onDestroy()

    fun onRegisterClicked()
}