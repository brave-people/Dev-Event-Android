/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventRepositoryImpl.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:05
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import javax.inject.Inject
import team.brave.devevent.android.data.mapper.toDomain
import team.brave.devevent.android.data.model.EventResponseItem
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.repository.EventRepository

class EventRepositoryImpl @Inject constructor(
    private val client: Fuel,
) : EventRepository {
    override suspend fun getAllEvents(): List<Event> {
        val deserializer = jacksonDeserializerOf<List<EventResponseItem>>()
        return client.get("/current").awaitObjectResult(deserializer).get().toDomain()
    }
}
