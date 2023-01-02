/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [data2domain.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:13
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.mapper

import androidx.annotation.VisibleForTesting
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
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
            // require fields assertion
            event.title ?: return@nestedMapNotNull null
            event.organizer ?: return@nestedMapNotNull null
            event.startDateTime ?: return@nestedMapNotNull null
            event.endDateTime ?: return@nestedMapNotNull null
            event.eventTimeType ?: return@nestedMapNotNull null
            event.eventLink ?: return@nestedMapNotNull null

            Event(
                title = event.title,
                organizer = event.organizer,
                time = (event.startDateTime to event.endDateTime).toEventDateString() ?: "조회 실패",
                timeType = EventTimeType.valueOf(event.eventTimeType),
                tags = event.tags?.filterNotNull()?.mapNotNull(TagData::toDomain).orEmpty(),
                eventLink = event.eventLink,
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

/**
 * 이벤트 시작일과 종료일을 [Pair] 로 받고,
 * 이벤트 카드에 표시할 형식에 맞게 다듬어 반환합니다.
 *
 * ### 표시 조건
 *
 * - 시작일과 종료일이 같은 경우: yyyy.MM.dd(E) HH:mm ~ HH:mm
 * - 시작일과 종료일이 다른 경우: yyyy.MM.dd(E) ~ yyyy.MM.dd(E)
 */
@VisibleForTesting
internal fun Pair<String, String>.toEventDateString(): String? {
    val startDate = first.toDate() ?: return null
    val endDate = second.toDate() ?: return null

    return if (startDate.isSameDay(endDate)) {
        // 시작일과 종료일이 같은 경우
        val startTime = SimpleDateFormat("yyyy.MM.dd(E) HH:mm", Locale.KOREA).format(startDate)
        val endTime = SimpleDateFormat("HH:mm", Locale.KOREA).format(endDate)
        "$startTime ~ $endTime"
    } else {
        // 시작일과 종료일이 다른 경우
        val dateFormat = SimpleDateFormat("yyyy.MM.dd(E)", Locale.KOREA)
        val startTime = dateFormat.format(startDate)
        val endTime = dateFormat.format(endDate)
        "$startTime ~ $endTime"
    }
}

// 2022-12-15T19:26
private const val EventRawDateFormat = "yyyy-MM-dd'T'HH:mm"

@VisibleForTesting
internal fun String.toDate(): Date? {
    val formatter = SimpleDateFormat(EventRawDateFormat, Locale.KOREA)
    return formatter.parse(this)
}

@VisibleForTesting
internal fun Date.isSameDay(other: Date): Boolean {
    val cal1 = Calendar.getInstance().also { time = time }
    val cal2 = Calendar.getInstance().also { time = other.time }
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}
