package dev.adriankuta.callmonitor.app.tools

import dev.adriankuta.callmonitor.model.CallLog

interface CallLogManager {

    fun startCallMonitoring()

    fun stopCallMonitoring()

    suspend fun getCallHistory(): List<CallLog>

}