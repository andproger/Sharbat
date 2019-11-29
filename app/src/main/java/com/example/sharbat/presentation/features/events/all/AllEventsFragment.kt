package com.example.sharbat.presentation.features.events.all

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import com.example.sharbat.R
import com.example.sharbat.domain.utils.toDateText
import com.example.sharbat.presentation.features.events.BaseEventsFragment
import com.example.sharbat.presentation.features.events.EventsView
import kotlinx.android.synthetic.main.fragment_all_events.*
import java.util.*

class AllEventsFragment : BaseEventsFragment<AllEventsView, AllEventsPresenter>() {

    override fun contentResId() = R.layout.fragment_all_events

    private var dialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFilters()
        initSwipeRefreshLayout()
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter?.onRefresh()
        }
    }

    override fun createView(): AllEventsView {
        return object : AllEventsView, EventsView by this {

            override fun showProgress(show: Boolean) {
                swipeRefreshLayout.isRefreshing = show
            }

            override fun showError() {
                //TODO
            }

            override fun showDatePickerDialog(date: Date?) {
                openDatePickerDialog(date)
            }

            override fun showTypePickerDialog() {

            }

            override fun showPlacePickerDialog() {

            }

            override fun showSelectedDate(date: Date?) {
                textViewFilterDate.text = date?.toDateText() ?: "Date"
            }
        }
    }

    private fun openDatePickerDialog(date: Date?) {
        val myCalendar = Calendar.getInstance()

        if (date != null) {
            myCalendar.time = date
        }

        val onDateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                presenter?.onDateSelected(myCalendar.time)
            }

        DatePickerDialog(
            context!!,
            onDateSetListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun initFilters() {
        textViewFilterDate.setOnClickListener {
            presenter?.onFilterDateClicked()
        }

        textViewFilterType.setOnClickListener {
            presenter?.onFilterTypeClicked()
        }

        textViewFilterPlace.setOnClickListener {
            presenter?.onFilterPlaceClicked()
        }
    }

    override fun createPresenter() = AllEventsPresenterImpl(
        provider.provideGetAllEventsInteractor(),
        provider.provideRefreshEventsInteractor()
    )
}