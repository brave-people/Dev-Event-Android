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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import team.bravepeople.devevent.activity.main.event.database.EventEntity

@HiltViewModel
class EventViewModel @Inject constructor(
    private val database: EventDatabase
) : ViewModel() {

    private val _eventEntityList: MutableList<EventEntity> = mutableListOf()
    private val eventEntityList
        get() = _eventEntityList
            .sortedByDescending { it.name }
            .sortedByDescending { it.headerDate }
            .asReversed()
            .distinct() // List

    private val _eventEntityFlow = MutableStateFlow<List<EventEntity>>(emptyList())
    val eventEntityFlow = _eventEntityFlow.asStateFlow() // flow; unnecessary getter


    fun getAllTags() =
        eventEntityFlow.value
            .mapNotNull { it.category }
            .flatMap { it.split(",") } // .flatten()?
            .distinct()
            .sorted()

    fun updateEvent() {

    }

    fun clearEvents() {
        _eventEntityList.clear()
    }

    init {
        
    }
}
