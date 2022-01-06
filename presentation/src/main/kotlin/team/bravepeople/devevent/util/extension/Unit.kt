/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Unit.kt] created by Ji Sungbin on 21. 6. 27. 오전 2:05.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

import kotlinx.coroutines.delay

suspend fun doDelay(ms: Long, block: () -> Unit) {
    delay(ms)
    block()
}
