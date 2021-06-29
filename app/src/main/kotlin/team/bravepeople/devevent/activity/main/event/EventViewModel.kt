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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import team.bravepeople.devevent.activity.main.event.database.EventEntity

class EventViewModel private constructor() : ViewModel() {

    private val _eventEntityList: MutableList<EventEntity> = mutableListOf()
    val eventEntityList
        get() = _eventEntityList
            .sortedByDescending { it.name }
            .sortedByDescending { it.headerDate }
            .asReversed()
            .distinct() // List

    private val _eventEntityFlow = MutableStateFlow<List<EventEntity>>(emptyList())
    val eventEntityFlow = _eventEntityFlow.asStateFlow() // flow; unnecessary getter

    fun getAllTags() =
        eventEntityFlow.value.mapNotNull { it.category }.flatMap { it.split(",") } // .flatten()?

    fun updateEventFlow() {
        _eventEntityFlow.value = eventEntityList
    }

    fun updateEvent(eventEntity: EventEntity) {
        _eventEntityList.removeIf { it.name == eventEntity.name }
        _eventEntityList.add(eventEntity)
    }

    fun clearEvents() {
        _eventEntityList.clear()
    }

    fun addEvent(eventEntity: EventEntity) {
        _eventEntityList.add(eventEntity)
    }

    fun addEvents(eventEntities: List<EventEntity>) {
        this._eventEntityList.addAll(eventEntities)
    }

    companion object {
        val instance by lazy { EventViewModel() }
    }
}
