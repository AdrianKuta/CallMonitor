package dev.adriankuta.callmonitor.app.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.telephony.TelephonyManager
import androidx.core.content.edit
import dagger.hilt.android.AndroidEntryPoint
import dev.adriankuta.callmonitor.R
import javax.inject.Inject

@AndroidEntryPoint
class PhoneStateReceiver : BroadcastReceiver() {

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val ongoingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            if (state != TelephonyManager.EXTRA_STATE_IDLE && ongoingNumber != null) {
                saveOngoingCall(context, ongoingNumber)
            } else {
                clearOngoingCall(context)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveOngoingCall(context: Context, number: String) {
        preferences.edit {
            putString(context.getString(R.string.saved_ongoing_call), number)
        }
    }

    private fun clearOngoingCall(context: Context) {
        preferences.edit {
            remove(context.getString(R.string.saved_ongoing_call))
        }
    }
}