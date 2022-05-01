package dev.adriankuta.callmonitor.server.endpointHandlers

import android.util.Log
import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import dev.adriankuta.callmonitor.app.tools.CallLogManager
import dev.adriankuta.callmonitor.server.response.ResponseHandler
import javax.inject.Inject

class LogHandler @Inject constructor(
    private val responseHandler: ResponseHandler,
    private val callLogManager: CallLogManager
): HttpHandler {

    override fun handle(exchange: HttpExchange?) {
        when (exchange?.requestMethod) {
            "GET" -> sendCallLogs(exchange)
        }
    }

    private fun sendCallLogs(exchange: HttpExchange) {
        val callLogs = callLogManager.getCallHistory()
        val jsonResponse = Gson().toJson(callLogs)
        responseHandler.sendResponse(exchange, jsonResponse)
    }
}