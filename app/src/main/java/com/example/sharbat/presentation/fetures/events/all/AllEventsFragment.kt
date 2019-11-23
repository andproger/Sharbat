package com.example.sharbat.presentation.fetures.events.all

import android.os.Bundle
import android.view.View
import com.example.sharbat.R
import com.example.sharbat.presentation.fetures.events.BaseEventsFragment
import com.example.sharbat.presentation.fetures.events.EventsView
import kotlinx.android.synthetic.main.fragment_all_events.*

class AllEventsFragment : BaseEventsFragment<AllEventsView, AllEventsPresenter>() {

    override fun contentResId() = R.layout.fragment_all_events

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
            override fun renderFilters() {

            }

            override fun showProgress(show: Boolean) {
                swipeRefreshLayout.isRefreshing = show
            }
        }
    }

    private fun initFilters() {

    }

    override fun createPresenter() = AllEventsPresenterImpl(
        provider.provideGetAllEventsInteractor(),
        provider.provideRefreshEventsInteractor()
    )
}