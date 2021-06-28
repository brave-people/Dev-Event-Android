/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [NotificationUtil.kt] created by Ji Sungbin on 21. 6. 28. 오후 9:55.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build

@Suppress("DEPRECATION")
object NotificationUtil {
    fun createChannel(context: Context, name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getManager(context).createNotificationChannelGroup(
                NotificationChannelGroup(
                    name,
                    name
                )
            )

            val channelMessage =
                NotificationChannel(name, name, NotificationManager.IMPORTANCE_DEFAULT)
            channelMessage.description = description
            channelMessage.group = name
            channelMessage.enableVibration(false)
            getManager(context).createNotificationChannel(channelMessage)
        }
    }

    private fun getManager(context: Context) =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun getNormalNotification(
        context: Context,
        title: String,
        content: String,
        icon: Int,
        isOnGoing: Boolean
    ): Notification.Builder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(context, title)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(icon)
                .setAutoCancel(true)
                .setOngoing(isOnGoing)
        } else {
            Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(icon)
                .setAutoCancel(true)
                .setOngoing(isOnGoing)
        }
    }
}
