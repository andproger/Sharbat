package com.example.sharbat.presentation.features.events.my

import android.os.Bundle
import android.view.View
import com.example.sharbat.R
import com.example.sharbat.presentation.features.events.BaseEventsFragment
import com.example.sharbat.presentation.features.events.EventsView

class MyEventsFragment : BaseEventsFragment<MyEventsView, MyEventsPresenter>() {

    override fun contentResId() = R.layout.fragment_my_events

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFilters()
    }

    private fun initFilters() {

    }

    override fun createView(): MyEventsView {
        return object : MyEventsView, EventsView by this {

        }
    }

    override fun createPresenter() = MyEventsPresenterImpl(
        provider.provideGetMyEventsInteractor()
    )
}