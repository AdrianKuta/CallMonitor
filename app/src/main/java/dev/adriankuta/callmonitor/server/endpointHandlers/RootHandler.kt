package dev.adriankuta.callmonitor.server.endpointHandlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import dev.adriankuta.callmonitor.server.response.ResponseHandler
import javax.inject.Inject

class RootHandler @Inject constructor(
    private val responseHandler: ResponseHandler
) : HttpHandler {

    override fun handle(exchange: HttpExchange?) {
        when (exchange?.requestMethod) {
            "GET" -> responseHandler.sendResponse(exchange, "Welcome to my server!")
        }
    }
}