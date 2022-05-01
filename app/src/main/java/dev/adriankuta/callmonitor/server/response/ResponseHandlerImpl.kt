package dev.adriankuta.callmonitor.server.response

import com.sun.net.httpserver.HttpExchange
import javax.inject.Inject

class ResponseHandlerImpl @Inject constructor() : ResponseHandler {

    override fun sendResponse(exchange: HttpExchange, response: String) {
        exchange.sendResponseHeaders(200, response.length.toLong())
        exchange.responseBody.use {
            it.write(response.toByteArray())
        }
    }
}