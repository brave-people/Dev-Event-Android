/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventRepositoryImpl.kt] created by Ji Sungbin on 21. 7. 2. 오전 3:54.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.event.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import team.bravepeople.devevent.event.domain.Event
import team.bravepeople.devevent.event.domain.EventRepo
import team.bravepeople.devevent.util.extension.parseOrNull
import kotlin.coroutines.resume

class EventRepoImpl(private val client: EventService) : EventRepo {
    @Suppress("BlockingMethodInNonBlockingContext", "LocalVariableName")
    override suspend fun load(coroutineScope: CoroutineScope): Result<List<Event>> =
        suspendCancellableCoroutine { continuation ->
            coroutineScope.launch {
                val result = client.getEventPage()
                if (result.isSuccessful) {
                    val _events = result.body()!!.use { it.string() }
                    val events = parseEvents(_events)
                    continuation.resume(Result.success(events))
                } else {
                    val exceptionMessage = result.errorBody()?.use { it.string() } ?: "이벤트 불러오기 실패"
                    continuation.resume(Result.failure(Exception(exceptionMessage)))
                }
            }
        }

    private fun parseEvents(value: String): List<Event> {
        val eventList = mutableListOf<Event>()

        fun String?.polish(removeWhiteSpace: Boolean = true): String? {
            return if (this != null) {
                var content = replaceFirst("-", "").replaceFirst(":", "")
                    .replace("`", "").split("\n")[0].trim()
                if (removeWhiteSpace) content = content.replace(" ", "")
                content
            } else null
        }

        val data = value.split("자주 확인하여 참석합시다 :)")[1].split("---------------")[0]
        data.split("##").forEachIndexed { eventsIndex, events ->
            if (eventsIndex > 0) {
                val headerDate = events.split("\n")[0].trim()
                events.split("- __").forEachIndexed { eventIndex, event ->
                    if (eventIndex > 0) {
                        event.run {
                            var site: String? = null
                            val name: String

                            if (contains("http")) {
                                name = "__$this".split("__[")[1].split("](http")[0]
                                site = "http" + split("(http")[1].split(")")[0]
                            } else {
                                name = split("__")[0]
                            }

                            val category = parseOrNull("- 분류").polish()
                            val joinDate =
                                (parseOrNull("- 신청") ?: parseOrNull("- 모집")).polish()
                            val startDate = parseOrNull("- 일시").polish()
                            val owner = parseOrNull("- 주최").polish(false)

                            eventList.add(
                                Event(
                                    site = site,
                                    name = name,
                                    category = category,
                                    headerDate = headerDate,
                                    joinDate = joinDate,
                                    startDate = startDate,
                                    owner = owner
                                )
                            )
                        }
                    }
                }
            }
        }
        return eventList
    }
}
