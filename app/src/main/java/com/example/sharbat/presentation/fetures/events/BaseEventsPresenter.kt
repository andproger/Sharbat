package com.example.sharbat.presentation.fetures.events

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import com.example.sharbat.domain.utils.toDateText
import com.example.sharbat.domain.utils.toTimeText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

abstract class BaseEventsPresenter<V : EventsView>(
    private val getEventsInteractor: GetEventsInteractor
) : EventsPresenter<V> {

    protected var events: List<Event> = listOf()
    protected var selectedFilterDate: Date? = null

    protected var view: V? = null
    protected val compositeDisposable = CompositeDisposable()

    init {
        getEventsInteractor.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { events ->
                this.events = events

                val eventsViewState = events.withFilters().map { it.toViewState() }

                view?.showEvents(eventsViewState)
            }.let { compositeDisposable.add(it) }
    }

    override fun attachView(view: V) {
        this.view = view
    }

    override fun onItemClicked(id: String) {
        view?.openEvent(id)
    }

    override fun onDestroy() {
        view = null
        compositeDisposable.dispose()
    }

    fun Event.toViewState() = EventViewState(
        id,
        title,
        eventTime.toDateText(),
        eventTime.toTimeText(),
        place
    )

    protected fun List<Event>.withFilters(): List<Event> {
        return selectedFilterDate?.let { date ->
            this.filter { event ->
                event.eventTime.time >= date.time
            }
        } ?: this
    }
}
