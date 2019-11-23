package com.example.sharbat.presentation.fetures.events.all

import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import com.example.sharbat.domain.interactors.events.RefreshEventsInteractor
import com.example.sharbat.presentation.fetures.events.BaseEventsPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllEventsPresenterImpl(
    getEventsInteractor: GetEventsInteractor,
    private val refreshEventsInteractor: RefreshEventsInteractor
) : BaseEventsPresenter<AllEventsView>(getEventsInteractor), AllEventsPresenter {

    private var refreshing = false

    override fun onRefresh() {
        refresh()
    }

    private fun refresh() {
        if (!refreshing) {
            refreshEventsInteractor.refresh()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    refreshing = true
                    view?.showProgress(refreshing)
                }
                .subscribe {
                    refreshing = false
                    view?.showProgress(refreshing)
                }.let { compositeDisposable.add(it) }
        }
    }
}