/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventResult.kt] created by Ji Sungbin on 22. 1. 7. 오전 12:17
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.domain.model

data class EventResult(val exception: Exception? = null, val events: List<Event>? = null) {
    fun isFailure() = exception != null
}
