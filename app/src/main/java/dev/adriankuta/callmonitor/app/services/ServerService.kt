package dev.adriankuta.callmonitor.app.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.sun.net.httpserver.HttpServer
import dagger.hilt.android.AndroidEntryPoint
import dev.adriankuta.callmonitor.R
import dev.adriankuta.callmonitor.app.ui.activities.MainActivity
import dev.adriankuta.callmonitor.app.ui.base.MyApplication.Companion.NOTIFICATION_CHANNEL_ID
import javax.inject.Inject

@AndroidEntryPoint
class ServerService: Service() {

    @Inject
    lateinit var httpServer: HttpServer

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        prepareForegroundNotification()
        httpServer.start()
    }

    private fun prepareForegroundNotification() {
        val pendingIntent = Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        }

        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(getText(R.string.call_monitoring_notification_title))
            .setContentText(getText(R.string.call_monitoring_notification_description))
            .setContentIntent(pendingIntent)
            .setTicker(getText(R.string.call_monitoring_notification_ticker))
            .build()

        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        httpServer.stop(0)
        super.onDestroy()
    }


    companion object {
        private const val ONGOING_NOTIFICATION_ID = 1
    }
}