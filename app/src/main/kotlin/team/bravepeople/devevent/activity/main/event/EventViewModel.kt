/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventViewModel.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:45.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import team.bravepeople.devevent.activity.main.event.database.EventEntity
import team.bravepeople.devevent.util.extension.parseOrNull

class EventViewModel private constructor() : ViewModel() {

    private val databaseDao by lazy { EventDatabase.instance.dao() }
    private val _eventEntities: MutableList<EventEntity> = mutableListOf()

    val eventEntities: List<EventEntity> get() = _eventEntities

    fun updateEvent(eventEntity: EventEntity) {
        _eventEntities.removeIf { it.name == eventEntity.name }
        _eventEntities.add(eventEntity)
    }

    fun save() = viewModelScope.launch {
        if (databaseDao.getEvents().isNullOrEmpty()) {
            databaseDao.insertAll(eventEntities)
        } else {
            eventEntities.filterNot { event -> databaseDao.getEvents()!!.contains(event) }
            databaseDao.updateAll(eventEntities)
        }
    }

    fun parseAndSave(value: String) {
        fun String?.polish() =
            if (this != null) replaceFirst("-", "").replaceFirst(":", "")
                .replace("`", "").replace(" ", "").split("\n")[0].trim()
            else null

        val data = value.split("## Dev Event만의 특별함")[1].split("---------------")[0]
        data.split("##").forEachIndexed { eventsIndex, events ->
            if (eventsIndex > 0) {
                val headerDate = events.split("\n")[0].trim()
                events.split("- __").forEachIndexed { eventIndex, event ->
                    if (eventIndex > 0) {
                        println("AAAAAAAAAAAAAAAAA")
                        println(event)
                        event.run {
                            var site: String? = null
                            val name: String

                            if (contains("http")) {
                                name = split("[")[1].split("]")[0]
                                site = split("(")[1].split(")")[0]
                            } else {
                                name = split("__")[0]
                            }

                            val category = parseOrNull("- 분류").polish()
                            val joinDate = (parseOrNull("- 신청") ?: parseOrNull("- 모집")).polish()
                            val startDate = parseOrNull("- 일시").polish()
                            val owner = parseOrNull("- 주최").polish()

                            val eventEntity = EventEntity(
                                site = site,
                                name = name,
                                category = category,
                                headerDate = headerDate,
                                joinDate = joinDate,
                                startDate = startDate,
                                owner = owner
                            )

                            _eventEntities.add(eventEntity)
                            println(eventEntity)
                            println("AAAAAAAAAAAAAAAAA\n\n")
                        }
                    }
                }
            }
        }
    }

    companion object {
        val instance by lazy { EventViewModel() }
    }
}