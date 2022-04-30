package dev.adriankuta.callmonitor.calls

import dev.adriankuta.callmonitor.repositories.CallRepository
import dev.adriankuta.callmonitor.services.CallService
import javax.inject.Inject

class CallLogManager @Inject constructor(
    private val callRepository: CallRepository
) {
    fun getCallHistory() =
        callRepository.getCallHistory()

}