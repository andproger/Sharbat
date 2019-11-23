package com.example.sharbat.domain.interactors.events

import com.example.sharbat.data.network.ClientApi
import com.example.sharbat.data.network.entity.EventResponse
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.utils.toDate
import io.reactivex.Completable
import io.reactivex.Single

class RefreshEventsInteractorImpl(
    private val clientApi: ClientApi,
    private val eventsRepository: EventsRepository
) : RefreshEventsInteractor {

    override fun refresh(): Single<RefreshResult> {
        return clientApi.getAllEvents()
                //TODO mock
            .onErrorReturn {
                listOf(
                    EventResponse(
                        "1",
                        "1",
                        "1",
                        "https://game-tournaments.com/media/logo/t13366.png",
                        "1",
                        "1",
                        "1",
                        "1"
                    ),
                    EventResponse(
                        "2",
                        "2",
                        "2",
                        "https://game-tournaments.com/media/logo/t13366.png",
                        "1",
                        "1",
                        "1",
                        "1"
                    ),
                    EventResponse(
                        "3",
                        "1",
                        "1",
                        "https://game-tournaments.com/media/logo/t13366.png",
                        "1",
                        "1",
                        "1",
                        "1"
                    ),
                    EventResponse(
                        "4",
                        "2",
                        "2",
                        "https://game-tournaments.com/media/logo/t13366.png",
                        "1",
                        "1",
                        "1",
                        "1"
                    )
                )
            }
            .map { response ->
                val events = response.map { it.toCore() }

                eventsRepository.save(events)

                SuccessResult as RefreshResult
            }.onErrorReturn {
                //TODO handling
                FailedResult(ErrorType.UNKNOWN_ERROR)
            }
    }

    fun EventResponse.toCore() = Event(
        id = id,
        title = title,
        text = text,
        image = image,
        eventTime = timeEvent.toDate(),
        updateTime = timeStamp.toDate(),
        place = place,
        link = link
    )
}