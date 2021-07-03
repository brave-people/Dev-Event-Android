/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [ForegroundService.kt] created by Ji Sungbin on 21. 7. 2. 오후 11:11.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import team.bravepeople.devevent.R
import team.bravepeople.devevent.util.NotificationUtil

class ForegroundService : Service() {

    private val pm by lazy { getSystemService(Context.POWER_SERVICE) as PowerManager }
    private val wakeLock by lazy { pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, wakeLockLabel) }
    private val wakeLockLabel by lazy { getString(R.string.app_name) } // Context.getString() -> by lazy
    private val notificationId = 1000
    private val notification by lazy {
        NotificationUtil.getNormalNotification(
            applicationContext,
            applicationContext.getString(R.string.notification_channel_name),
            getString(R.string.app_name),
            getString(R.string.service_wait_receive_new_event),
            R.mipmap.ic_launcher,
            true
        )
    }

    override fun onBind(intent: Intent?): IBinder? = null

    @SuppressLint("WakelockTimeout")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(notificationId, notification.build())
        wakeLock.acquire()
        return START_STICKY
    }

    override fun onDestroy() {
        stopForeground(true)
        wakeLock.release()
    }
}
