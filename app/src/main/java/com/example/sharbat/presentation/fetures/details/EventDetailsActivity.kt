package com.example.sharbat.presentation.fetures.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.sharbat.R
import com.example.sharbat.data.db.model.EventModel
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.utils.toDateText
import com.example.sharbat.presentation.app.App
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity(), EventDetailsView {

    private val id get() = intent.getStringExtra("id")

    private var presenter: EventDetailsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        initViews()
        setupPresenter()
    }

    override fun renderStar(show: Boolean) {
        if (show) {
            starEvent.setImageResource(R.drawable.ic_star_active)
        } else {
            starEvent.setImageResource(R.drawable.ic_star)
        }
    }

    override fun renderContent(event: Event) {
        timeEvent.text = event.eventTime.toDateText()
        whereEvent.text = event.place
        textEvent.text = event.text

        Picasso.get().load(event.image).into(imageEvent)
    }

    private fun initViews() {
        starEvent.setOnClickListener {
            presenter?.onStarClicked()
        }
    }

    private fun setupPresenter() {
        val provider = (application as App).getProvider()

        presenter = EventDetailsPresenterImpl(
            provider.provideMarkAsFavoriteInteractor(),
            provider.providerGetEventInteractor(),
            id
        )

        presenter?.attachView(this)
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}