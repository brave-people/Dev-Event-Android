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
import dagger.hilt.android.HiltAndroidApp
import team.bravepeople.devevent.util.NotificationUtil

@HiltAndroidApp
class DevEventAndroid : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationUtil.createChannel( // todo: Check channel already created
            applicationContext,
            getString(R.string.notification_channel_name),
            getString(R.string.notification_channel_description)
        )
    }
}
