package com.example.sharbat.presentation.features.events.my

import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import com.example.sharbat.presentation.features.events.BaseEventsPresenter

class MyEventsPresenterImpl(
    getEventsInteractor: GetEventsInteractor
) : BaseEventsPresenter<MyEventsView>(getEventsInteractor), MyEventsPresenter {

}