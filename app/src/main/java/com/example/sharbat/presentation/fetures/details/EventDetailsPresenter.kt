package com.example.sharbat.presentation.fetures.details

interface EventDetailsPresenter {
    fun attachView(view: EventDetailsView)

    fun onStarClicked()

    fun onDestroy()
}