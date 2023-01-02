/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [data2domain.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:13
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.mapper

import team.brave.devevent.android.data.model.EventResponseItem
import team.brave.devevent.android.data.model.TagData
import team.brave.devevent.android.data.util.generateRandomHexColor
import team.brave.devevent.android.domain.constants.EventTimeType
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.model.Tag

internal fun List<EventResponseItem>.toDomain(): List<Event> {
    val events = mapNotNull { item ->
        val events = item.events?.filterNotNull() ?: return@mapNotNull null
        events.mapNotNull nestedMapNotNull@{ event ->
            Event(
                title = event.title ?: return@nestedMapNotNull null,
                organizer = event.organizer ?: return@nestedMapNotNull null,
                time = TODO(),
                timeType = event.eventTimeType?.let { type -> EventTimeType.valueOf(type) }
                    ?: return@nestedMapNotNull null,
                tags = event.tags?.filterNotNull()?.mapNotNull(TagData::toDomain) ?: emptyList(),
                eventLink = event.eventLink ?: return@nestedMapNotNull null,
                bannerUrl = event.coverImageLink.orEmpty(),
            )
        }
    }
    return events.flatten()
}

private fun TagData.toDomain(): Tag? {
    return Tag(
        name = name ?: return null,
        hexColor = hexColor ?: generateRandomHexColor(),
    )
}
