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

@Suppress("StaticFieldLeak")
object AlarmUtil {

    @Suppress("ObjectPropertyName")
    private var _context: Context? = null
    private val context by lazy { _context!! }

    private val alarmManager by lazy { context.getSystemService(Context.ALARM_SERVICE) as AlarmManager }
    private val intent by lazy { Intent(context, EventReloadReceiver::class.java) }

    @Suppress("UnspecifiedImmutableFlag")
    private val pendingIntent by lazy {
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun cancelReloadTask() {
        alarmManager.cancel(pendingIntent)
    }

    fun addReloadTask() {
        val calender = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 13)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calender.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun init(context: Context) {
        this._context = context
    }

    fun destroy() {
        _context = null
    }
}
