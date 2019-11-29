package com.example.sharbat.presentation.features.events.all

import com.example.sharbat.domain.interactors.events.FailedResult
import com.example.sharbat.domain.interactors.events.GetEventsInteractor
import com.example.sharbat.domain.interactors.events.RefreshEventsInteractor
import com.example.sharbat.presentation.features.events.BaseEventsPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class AllEventsPresenterImpl(
    getEventsInteractor: GetEventsInteractor,
    private val refreshEventsInteractor: RefreshEventsInteractor
) : BaseEventsPresenter<AllEventsView>(getEventsInteractor), AllEventsPresenter {

    private var refreshing = false

    override fun onRefresh() {
        refresh()
    }

    override fun onFilterDateClicked() {
        view?.showDatePickerDialog(selectedFilterDate)
    }

    override fun onFilterTypeClicked() {
        view?.showTypePickerDialog()
    }

    override fun onFilterPlaceClicked() {
        view?.showPlacePickerDialog()
    }

    override fun onDateSelected(time: Date?) {
        time?.let { date ->
            selectedFilterDate = date

            view?.showSelectedDate(selectedFilterDate)
            view?.showEvents(events.withFilters().map { it.toViewState() })
        }
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
                .subscribe { result ->
                    refreshing = false
                    view?.showProgress(refreshing)
                    if (result is FailedResult) view?.showError()
                }.let { compositeDisposable.add(it) }
        }
    }
}