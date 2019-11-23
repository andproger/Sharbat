package com.example.sharbat.presentation.fetures.events

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import com.example.sharbat.presentation.utils.toDateText
import com.example.sharbat.presentation.utils.toTimeText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

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
                val eventsViewState = events.map { event ->
                    val date = Date(event.eventTime)
                    event.toViewState(date)
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

    fun Event.toViewState(date: Date) = EventViewState(
        id,
        title,
        date.toDateText(),
        date.toTimeText(),
        place
    )
}