package com.example.sharbat.presentation.features.details

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.interactors.events.GetEventInteractor
import com.example.sharbat.domain.interactors.events.favorite.MarkAsFavoriteInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EventDetailsPresenterImpl(
    private val markAsFavoriteInteractor: MarkAsFavoriteInteractor,
    private val getEventInteractor: GetEventInteractor,
    private val id: String
) : EventDetailsPresenter {

    private val compositeDisposable = CompositeDisposable()
    private var event: Event? = null

    init {
        getEventInteractor.get(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { eventContract ->
                event = eventContract.event
                view?.renderContent(eventContract.event)
                view?.renderStar(eventContract.favorite)
            }.let { compositeDisposable.add(it) }
    }

    var view: EventDetailsView? = null

    override fun attachView(view: EventDetailsView) {
        this.view = view
    }

    override fun onStarClicked() {
        markAsFavoriteInteractor.mark(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().let { compositeDisposable.add(it) }
    }

    override fun onRegisterClicked() {
        event?.let {
            view?.register(it.link)
        }
    }

    override fun onDestroy() {
        view = null
    }
}