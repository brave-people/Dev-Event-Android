/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventViewModel.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:45.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.event

import team.bravepeople.devevent.database.EventEntity

class EventViewModel private constructor() {

    private val _eventEntities: MutableList<EventEntity> = mutableListOf()

    val eventEntity: List<EventEntity> get() = _eventEntities

    fun addEvent(eventEntity: EventEntity) {
        _eventEntities.add(eventEntity)
    }

    fun updateEvent(eventEntity: EventEntity) {
        _eventEntities.removeIf { it.name == eventEntity.name }
        addEvent(eventEntity)
    }

    fun saveEvents() {

    }

    companion object {
        val instance by lazy { EventViewModel() }
    }
}