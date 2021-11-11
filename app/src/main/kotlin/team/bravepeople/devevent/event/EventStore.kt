package team.bravepeople.devevent.event

import team.bravepeople.devevent.event.domain.Event

object EventStore {
    @Suppress("ObjectPropertyName")
    private val _events = mutableListOf<Event>()
    val events: List<Event>
        get() = _events
            .sortedByDescending { it.name }
            .sortedByDescending { it.headerDate }
            .asReversed()
            .distinct()

    val getAllTags
        get() = events
            .mapNotNull { it.category }
            .flatMap { it.split(",") }
            .distinct()
            .sorted()

    fun update(events: List<Event>) {
        _events.addAll(events)
    }
}
