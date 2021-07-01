/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventRepoResult.kt] created by Ji Sungbin on 21. 7. 2. 오전 3:54.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event.repo

import team.bravepeople.devevent.activity.main.event.database.EventEntity

sealed class EventRepoResult {
    data class Error(val exception: Exception) : EventRepoResult()
    data class Success(val events: List<EventEntity>) : EventRepoResult()
}

@Suppress("UNCHECKED_CAST")
fun Any?.toEventRepoResult(): EventRepoResult {
    return when (this) {
        is Exception -> EventRepoResult.Error(this)
        is List<*> -> EventRepoResult.Success(this as List<EventEntity>)
        else -> throw Error("Unknown type.")
    }
}