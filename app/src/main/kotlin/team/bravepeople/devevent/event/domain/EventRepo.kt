/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [GithubRepository.kt] created by Ji Sungbin on 21. 7. 2. 오전 3:27.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.event.domain

import kotlinx.coroutines.CoroutineScope

interface EventRepo {
    suspend fun load(coroutineScope: CoroutineScope): Result<List<Event>>
}
