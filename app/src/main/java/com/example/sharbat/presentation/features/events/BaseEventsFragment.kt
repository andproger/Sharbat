package com.example.sharbat.presentation.features.events

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sharbat.presentation.app.App
import com.example.sharbat.presentation.features.details.EventDetailsActivity
import kotlinx.android.synthetic.main.fragment_all_events.*

abstract class BaseEventsFragment<V : EventsView, P : EventsPresenter<V>> : Fragment(), EventsView {

    protected var presenter: P? = null
    private var view: V? = null

    override fun showEvents(events: List<EventViewState>) {
        adapter.update(events)
    }

    override fun openEvent(id: String) {
        startActivity(
            Intent(
                context,
                EventDetailsActivity::class.java
            ).putExtra("id", id)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentResId(), container, false)
    }

    abstract fun contentResId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        presenter = createPresenter()
        presenter?.attachView(createView())
    }

    abstract fun createView(): V

    abstract fun createPresenter(): P

    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = EventsAdapter(
            onItemClicked = { id ->
                presenter?.onItemClicked(id)
            }
        )
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    protected val adapter get() = recyclerView.adapter as EventsAdapter

    protected val provider get() = (activity!!.application as App).getProvider()
}