package com.example.sharbat.domain.interactors.events

import io.reactivex.Single

interface RefreshEventsInteractor {
    fun refresh(): Single<RefreshResult>
}

sealed class RefreshResult

object SuccessResult : RefreshResult()

class FailedResult(
    val errorType: ErrorType
) : RefreshResult()

enum class ErrorType {
    INTERNAL_SERVER_ERROR,
    CONNECTION_ERROR,
    UNKNOWN_ERROR
}