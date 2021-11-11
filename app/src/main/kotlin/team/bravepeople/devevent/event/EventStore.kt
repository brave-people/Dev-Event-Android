package team.bravepeople.devevent.event

import team.bravepeople.devevent.event.domain.Event

object EventStore {
    val events = mutableListOf<Event>()
}
