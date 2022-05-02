package dev.adriankuta.callmonitor.app.tools

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.adriankuta.callmonitor.R
import dev.adriankuta.callmonitor.app.services.ServerService
import dev.adriankuta.callmonitor.model.CallLog
import dev.adriankuta.callmonitor.repositories.calls.CallRepository
import dev.adriankuta.callmonitor.repositories.utils.toLogicModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CallLogManagerImpl @Inject constructor(
    private val callRepository: CallRepository,
    @ApplicationContext private val context: Context,
    private val preferences: SharedPreferences
) : CallLogManager {

    override fun startCallMonitoring() {
        val intent = Intent(context, ServerService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
        saveStartDate()
    }

    override fun stopCallMonitoring() {
        val intent = Intent(context, ServerService::class.java)
        context.stopService(intent)
        clearData()
    }

    override suspend fun getCallHistory(): List<CallLog> {
        return withContext(Dispatchers.IO) {

            callRepository.getCallHistory(getLatestStartDate()).map {
                val queryCount = preferences.getInt(it.number, 0)
                it.toLogicModel(queryCount)
            }
        }
    }

    private fun saveStartDate() {
        val startDate = Date().time
        preferences.edit {
            putLong(context.getString(R.string.saved_start_call_monitoring), startDate)
            apply()
        }

    }

    private fun getLatestStartDate(): Long {
        return preferences.getLong(context.getString(R.string.saved_start_call_monitoring), -1)
    }

    private fun clearData() {
        preferences.edit {
            clear()
        }
    }

}