package dev.adriankuta.callmonitor.app.tools

import dev.adriankuta.callmonitor.model.CallLog
import dev.adriankuta.callmonitor.repositories.calls.CallRepository
import dev.adriankuta.callmonitor.repositories.utils.toLogicModel
import javax.inject.Inject

class CallLogManagerImpl @Inject constructor(
    private val callRepository: CallRepository
) : CallLogManager {

    override fun getCallHistory(): List<CallLog> {
        return callRepository.getCallHistory().map { it.toLogicModel(0) }
    }

}