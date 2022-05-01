package dev.adriankuta.callmonitor.repositories.utils

import dev.adriankuta.callmonitor.model.CallLog
import dev.adriankuta.callmonitor.repositories.entities.CallLogEntity

fun CallLogEntity.toLogicModel(timesQueries: Int): CallLog {
    return CallLog(
        beginning = date,
        duration,
        number,
        name ?: number,
        timesQueries
    )
}