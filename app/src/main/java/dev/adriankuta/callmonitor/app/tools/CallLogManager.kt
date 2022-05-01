package dev.adriankuta.callmonitor.app.tools

import dev.adriankuta.callmonitor.model.CallLog

interface CallLogManager {
    fun getCallHistory(): List<CallLog>

}