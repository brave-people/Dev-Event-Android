/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [convert.kt] created by Ji Sungbin on 23. 1. 19. 오후 8:51
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.util

import android.content.res.Resources

val Int.dp2px: Int
    get() {
        val density = Resources.getSystem().displayMetrics.density
        return (this * density + 0.5f).toInt()
    }
