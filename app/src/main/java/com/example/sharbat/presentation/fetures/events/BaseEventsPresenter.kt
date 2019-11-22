package com.example.sharbat.presentation.fetures.events

import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseEventsPresenter<V : EventsView>(
    private val getEventsInteractor: GetEventsInteractor
) : EventsPresenter<V> {

    protected var view: V? = null
    protected val compositeDisposable = CompositeDisposable()

    init {
        getEventsInteractor.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { events ->
                val eventsViewState = events.map {
                    with(it) {
                        EventViewState(
                            id,
                            title,
                            text
                        )
                    }
                }

                view?.showEvents(eventsViewState)
            }.let { compositeDisposable.add(it) }
    }

    override fun attachView(view: V) {
        this.view = view
    }

    override fun onItemClicked(id: Int) {
        view?.openEvent(id)
    }

    override fun onDestroy() {
        view = null
        compositeDisposable.dispose()
    }
}