package com.example.localnotification.manager

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.localnotification.MainActivity
import com.example.localnotification.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalNotificationManager @Inject
constructor(
    @ApplicationContext private val context: Context
) {
    fun showNotification(
        title: String,
        content: String,
        channelId: String = "1",
        notificationId: Int = 1
    ) {
        createNotificationChannel()

        val permission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        )

        if (permission != PackageManager.PERMISSION_GRANTED) return

        // 通知のビルダー設定
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // 適切なアイコンを設定
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getNotifyIntent(context)) // 通知をクリックしたときの動作
            .setAutoCancel(true) // クリックで通知を削除

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel() {
        val channelID = "1"
        val channelName = "通知チャンネル"
        val channelDescription = "このチャンネルではアプリの通知を管理します。"
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val existingChannel = notificationManager?.getNotificationChannel(channelID)

        if (existingChannel == null) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelID, channelName, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun getNotifyIntent(context: Context): PendingIntent {
        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(notifyIntent)
            getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }
    }
}
