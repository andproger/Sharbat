package com.example.sharbat.presentation.fetures.details

import com.example.sharbat.domain.interactors.events.GetEventInteractor
import com.example.sharbat.domain.interactors.events.MarkAsFavoriteInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EventDetailsPresenterImpl(
    private val markAsFavoriteInteractor: MarkAsFavoriteInteractor,
    private val getEventInteractor: GetEventInteractor,
    private val id: String
) : EventDetailsPresenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        getEventInteractor.get(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { eventContract ->
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

    override fun onDestroy() {
        view = null
    }
}