/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventRepositoryImpl.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:05
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import team.brave.devevent.android.data.mapper.toDomain
import team.brave.devevent.android.data.model.EventResponseItem
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.repository.EventRepository

class EventRepositoryImpl @Inject constructor(
    private val client: HttpClient,
) : EventRepository {
    override suspend fun getAllEvents(): List<Event> {
        val request = client.get("/current")
        val response: List<EventResponseItem> = request.body()
        return response.toDomain()
    }
}
