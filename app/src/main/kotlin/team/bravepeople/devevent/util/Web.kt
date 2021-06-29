/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Web.kt] created by Ji Sungbin on 21. 6. 27. 오전 1:35.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util

import android.app.PendingIntent
import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import team.bravepeople.devevent.R
import team.bravepeople.devevent.util.extension.toast


object Web {
    fun open(context: Context, url: String) {
        try {
            val builder = CustomTabsIntent.Builder()

            val organization = builder.build().intent
            organization.data = "https://github.com/brave-people".toUri()

            val projectGithubIntent = PendingIntent.getActivity(
                context,
                1000,
                organization,
                PendingIntent.FLAG_IMMUTABLE
            )
            builder.addMenuItem(context.getString(R.string.brave_people), projectGithubIntent)

            val customTabIntent = builder.build()
            customTabIntent.launchUrl(context, url.toUri())
        } catch (ignored: Exception) {
            toast(context, context.getString(R.string.web_non_install_browser))
        }
    }
}