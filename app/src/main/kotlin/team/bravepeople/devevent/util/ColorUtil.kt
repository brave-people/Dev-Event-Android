/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Color.kt] created by Ji Sungbin on 21. 6. 29. 오전 4:40.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util

object ColorUtil {
    fun getRandom() = (Math.random() * 16777215).toInt() or (0xFF shl 24)
}