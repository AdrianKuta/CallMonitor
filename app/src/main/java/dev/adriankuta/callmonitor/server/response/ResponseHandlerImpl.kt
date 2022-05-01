package dev.adriankuta.callmonitor.server.response

import com.sun.net.httpserver.HttpExchange
import javax.inject.Inject

class ResponseHandlerImpl @Inject constructor() : ResponseHandler {

    override fun sendResponse(exchange: HttpExchange, response: String) {
        try {
            val bs: ByteArray = response.toByteArray()
            exchange.responseHeaders.set("Content-Type", "application/json")
            exchange.sendResponseHeaders(200, bs.size.toLong())
            exchange.responseBody.use {
                it.write(bs)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}