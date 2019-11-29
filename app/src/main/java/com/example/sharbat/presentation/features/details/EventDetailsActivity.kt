package com.example.sharbat.presentation.features.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharbat.R
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.utils.toDateText
import com.example.sharbat.presentation.app.App
import com.example.sharbat.presentation.features.details.WebUtil.getCustomTabsPackages
import com.example.sharbat.presentation.features.details.WebUtil.launchCustomTab
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

    override fun register(link: String?) {
        link?.let {l ->
            if (!getCustomTabsPackages(this).isEmpty()) run {
                launchCustomTab(
                    this,
                    Uri.parse(l)
                )
            }
        }
    }

    private fun initViews() {
        starEvent.setOnClickListener {
            presenter?.onStarClicked()
        }

        btnRegistration.setOnClickListener {
            presenter?.onRegisterClicked()
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