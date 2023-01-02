/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [color.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:28
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.util

import java.util.Random

// https://stackoverflow.com/a/35459935/14299073
@Suppress("MagicNumber", "ImplicitDefaultLocale")
internal fun generateRandomHexColor(): String {
    val nextInt = Random().nextInt(0xffffff + 1)
    return String.format("#%06x", nextInt)
}
