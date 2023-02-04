/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DashboardArgument.kt] created by Ji Sungbin on 23. 2. 4. 오전 11:21
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.fragment.dashboard

import android.os.Bundle

data class DashboardArgument(val isFavorite: Boolean) {
    companion object {
        fun fromBundle(args: Bundle): DashboardArgument {
            return DashboardArgument(isFavorite = args.getBoolean("isFavorite"))
        }
    }
}
