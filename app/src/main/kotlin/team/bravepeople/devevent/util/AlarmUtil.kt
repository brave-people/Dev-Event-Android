/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [AlarmUtil.kt] created by Ji Sungbin on 21. 6. 28. 오후 9:57.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar
import team.bravepeople.devevent.receiver.EventReloadReceiver
import team.bravepeople.devevent.service.ForegroundService
import team.bravepeople.devevent.util.extension.isServiceRunning

object AlarmUtil {

    @Suppress("UnspecifiedImmutableFlag")
    private fun pendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, EventReloadReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun alarmManager(context: Context) =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun startReloadService(context: Context, addReloadTask: Boolean = true) {
        if (!context.isServiceRunning(ForegroundService::class.java)) {
            context.startService(Intent(context, ForegroundService::class.java))
            if (addReloadTask) addReloadTask(context)
        }
    }

    fun stopReloadService(context: Context, cancelReloadTask: Boolean = true) {
        if (context.isServiceRunning(ForegroundService::class.java)) {
            context.stopService(Intent(context, ForegroundService::class.java))
            if (cancelReloadTask) cancelReloadTask(context)
        }
    }

    private fun cancelReloadTask(context: Context) {
        alarmManager(context).cancel(pendingIntent(context))
    }

    private fun addReloadTask(context: Context) {
        val calender = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 13)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        alarmManager(context).setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calender.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent(context)
        )
    }
}
