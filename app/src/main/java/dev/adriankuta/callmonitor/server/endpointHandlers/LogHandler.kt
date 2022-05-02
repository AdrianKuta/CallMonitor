package dev.adriankuta.callmonitor.server.endpointHandlers

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import dev.adriankuta.callmonitor.app.tools.CallLogManager
import dev.adriankuta.callmonitor.server.response.ResponseHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogHandler @Inject constructor(
    private val responseHandler: ResponseHandler,
    private val callLogManager: CallLogManager,
    private val applicationScope: CoroutineScope,
    private val gson: Gson
) : HttpHandler {

    override fun handle(exchange: HttpExchange?) {
        when (exchange?.requestMethod) {
            "GET" -> sendCallLogs(exchange)
        }
    }

    private fun sendCallLogs(exchange: HttpExchange) {
        applicationScope.launch(Dispatchers.IO) {
            val callLogs = callLogManager.getCallHistory()
            val jsonResponse = gson.toJson(callLogs)
            responseHandler.sendResponse(exchange, jsonResponse)
        }
    }
}