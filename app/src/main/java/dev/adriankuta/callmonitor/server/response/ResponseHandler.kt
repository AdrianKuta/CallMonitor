package dev.adriankuta.callmonitor.server.response

import com.sun.net.httpserver.HttpExchange

interface ResponseHandler {
    fun sendResponse(exchange: HttpExchange, response: String)
}