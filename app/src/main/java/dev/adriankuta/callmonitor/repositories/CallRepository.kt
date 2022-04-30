package dev.adriankuta.callmonitor.repositories

import dev.adriankuta.callmonitor.repositories.entities.CallLogEntity

interface CallRepository {
    fun getCallHistory(): List<CallLogEntity>
}