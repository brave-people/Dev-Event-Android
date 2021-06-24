/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Event.kt] created by Ji Sungbin on 21. 6. 24. 오전 12:11.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import team.bravepeople.devevent.ui.searcher.LazySearcher

private val eventVm = EventViewModel.instance

@Composable
fun Event(eventFilter: EventFilter) { // todo
    var search by remember { mutableStateOf("") }
    val events = eventVm.eventEntities.filter {
        when (eventFilter) {
            EventFilter.None -> if (search.isNotBlank()) it.name.contains(search) else true
            EventFilter.Favorite -> it.favorite
        }
    }

    Column() {
        LazySearcher {
            search = value.text
        }
        LazyEvent(modifier = Modifier.padding(top = 8.dp), eventEntities = events)
    }
}