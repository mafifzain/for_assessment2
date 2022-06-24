package org.d3if0060.assessment2.data

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class TokoApplication : Application() {
    val database: TokoDb by lazy { TokoDb.getDataDb(this) }

    companion object{
        const val CHANNEL_ID = "channel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                "channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This is channel"
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }
}