/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Web.kt] created by Ji Sungbin on 21. 6. 27. 오전 1:35.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util

import android.app.Activity
import android.app.PendingIntent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

object Web {
    // todo: 크롬 사용 가능한지 체크
    fun open(activity: Activity, url: String) {
        val builder = CustomTabsIntent.Builder()

        val bravePeopleGithub = builder.build().intent
        bravePeopleGithub.data = "https://github.com/brave-people".toUri()

        val projectGithubIntent = PendingIntent.getActivity(
            activity,
            1000,
            bravePeopleGithub,
            PendingIntent.FLAG_IMMUTABLE
        )
        builder.addMenuItem("용감한 친구들", projectGithubIntent)

        val customTabIntent = builder.build()
        customTabIntent.launchUrl(activity, url.toUri())
    }
}