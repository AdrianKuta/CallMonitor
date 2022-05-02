package dev.adriankuta.callmonitor.server.endpointHandlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import javax.inject.Inject

class StatusHandler @Inject constructor(): HttpHandler {

    override fun handle(exchange: HttpExchange?) {
    }
}