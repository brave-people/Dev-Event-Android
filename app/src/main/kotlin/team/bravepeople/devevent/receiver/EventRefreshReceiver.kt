/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventReceiver.kt] created by Ji Sungbin on 21. 6. 28. 오후 9:54.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlin.random.Random
import team.bravepeople.devevent.R
import team.bravepeople.devevent.util.NotificationUtil

class EventRefreshReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        println("event reload receive")
        NotificationUtil.showNormalNotification(
            context!!,
            Random.nextInt(),
            context.getString(R.string.notification_channel_name),
            "1",
            "1",
            R.mipmap.ic_launcher,
            false
        )
    }
}
