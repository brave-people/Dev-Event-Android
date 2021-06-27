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
import team.bravepeople.devevent.activity.main.event.database.EventEntity

class EventViewModel private constructor() : ViewModel() {

    private val _eventEntities: MutableList<EventEntity> = mutableListOf()

    val eventEntities: List<EventEntity>
        get() = _eventEntities
            .sortedByDescending { it.name }
            .sortedByDescending { it.headerDate }.asReversed()
            .distinct()

    fun updateEvent(eventEntity: EventEntity) {
        _eventEntities.removeIf { it.name == eventEntity.name }
        _eventEntities.add(eventEntity)
    }

    fun addEvent(eventEntity: EventEntity) {
        _eventEntities.add(eventEntity)
    }

    fun addEvents(eventEntities: List<EventEntity>) {
        _eventEntities.addAll(eventEntities)
    }

    companion object {
        val instance by lazy { EventViewModel() }
    }
}