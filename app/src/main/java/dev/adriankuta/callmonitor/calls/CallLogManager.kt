package dev.adriankuta.callmonitor.calls

import dev.adriankuta.callmonitor.services.CallService
import javax.inject.Inject

class CallLogManager @Inject constructor(
    val callService: CallService
) {
}