package team.bravepeople.devevent.event

import team.bravepeople.devevent.domain.model.Event

object EventStore {
    @Suppress("ObjectPropertyName")
    private val _events = mutableListOf<team.bravepeople.devevent.domain.model.Event>()
    val events: List<team.bravepeople.devevent.domain.model.Event>
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

    fun update(events: List<team.bravepeople.devevent.domain.model.Event>) {
        _events.addAll(events)
    }
}
