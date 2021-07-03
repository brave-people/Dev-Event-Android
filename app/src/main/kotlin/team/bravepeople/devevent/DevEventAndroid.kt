/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [DevEventAndroid.kt] created by Ji Sungbin on 21. 6. 20. 오후 11:55.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent

import android.app.Application
import android.content.Intent
import dagger.hilt.android.HiltAndroidApp
import team.bravepeople.devevent.service.ForegroundService
import team.bravepeople.devevent.util.AlarmUtil
import team.bravepeople.devevent.util.Data
import team.bravepeople.devevent.util.NotificationUtil
import team.bravepeople.devevent.util.config.PathConfig

@HiltAndroidApp
class DevEventAndroid : Application() {
    override fun onCreate() {
        super.onCreate()
        val context = applicationContext
        AlarmUtil.init(context)

        NotificationUtil.createChannel( // todo: Check channel already created
            context,
            getString(R.string.notification_channel_name),
            getString(R.string.notification_channel_description)
        )

        startService(Intent(this, ForegroundService::class.java))
        AlarmUtil.addReloadTask()

        if (Data.read(context, PathConfig.EventRefreshDay, null) == null) {
            Data.save(context, PathConfig.EventRefreshDay, "0")
        }
        if (Data.read(context, PathConfig.NewEventNotification, null) == null) {
            Data.save(context, PathConfig.NewEventNotification, "false")
        }
    }
}
