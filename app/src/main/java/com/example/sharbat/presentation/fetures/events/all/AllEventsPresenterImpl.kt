package com.example.sharbat.presentation.fetures.events.all

import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import com.example.sharbat.presentation.fetures.events.BaseEventsPresenter

class AllEventsPresenterImpl(
    getEventsInteractor: GetEventsInteractor
) : BaseEventsPresenter<AllEventsView>(getEventsInteractor), AllEventsPresenter {

}