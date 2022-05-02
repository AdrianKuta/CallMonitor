package dev.adriankuta.callmonitor.server.endpointHandlers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.adriankuta.callmonitor.R
import dev.adriankuta.callmonitor.model.Status
import dev.adriankuta.callmonitor.server.response.ResponseHandler
import javax.inject.Inject

class StatusHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val responseHandler: ResponseHandler,
    private val gson: Gson,
    private val preferences: SharedPreferences
) : HttpHandler {

    override fun handle(exchange: HttpExchange) {
        val ongoingNumber =
            preferences.getString(context.getString(R.string.saved_ongoing_call), null)
        ongoingNumber?.let { incrementQueryCount(it) }
        val status = Status(ongoingNumber != null, ongoingNumber)
        val jsonResponse = gson.toJson(status)
        responseHandler.sendResponse(exchange, jsonResponse)
    }

    private fun incrementQueryCount(number: String) {
        val currentCount = preferences.getInt(number, 0)
        preferences.edit {
            putInt(number, currentCount + 1)
        }
    }
}