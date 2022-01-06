/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventRepositoryImpl.kt] created by Ji Sungbin on 22. 1. 7. 오전 12:27
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.data.repository

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import team.bravepeople.devevent.data.util.Network
import team.bravepeople.devevent.domain.model.EventResult
import team.bravepeople.devevent.domain.repository.EventRepository

class EventRepositoryImpl : EventRepository {
    override suspend fun load(context: Context, coroutineScope: CoroutineScope): EventResult {
        if (Network.isNetworkAvailable(context)) {
        } else {
        }
        TODO("Not yet implemented")
    }
}
