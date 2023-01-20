/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DevEventItem.kt] created by Ji Sungbin on 23. 1. 20. 오후 11:09
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.squareup.moshi.Json
import team.brave.devevent.android.domain.constants.EventTimeType

// [NOTE] eventTimeType 는 2가지 종류로 나뉨
// 1. DATE: 이벤트 진행 "일시" 를 나타냄
// 2. RECRUIT: 이벤트 "모집" 을 나타냄

// [NOTE] start_date_time ~ end_date_time 이 기간을 나타냄

internal data class EventData(
	@Json(name = "event_time_type")
	val eventTimeType: EventTimeType? = null,

	@Json(name = "start_day_week")
	val startDayWeek: String? = null,

	@Json(name = "end_time")
	val endTime: Any? = null,

	@Json(name = "description")
	val description: String? = null,

	@Json(name = "cover_image_link")
	val coverImageLink: String? = null,

	@Json(name = "title")
	val title: String? = null,

	@Json(name = "display_sequence")
	val displaySequence: Int? = null,

	@Json(name = "tags")
	val tags: List<TagData?>? = null,

	@Json(name = "start_time")
	val startTime: Any? = null,

	@Json(name = "end_date_time")
	val endDateTime: String? = null,

	@Json(name = "create_date_time")
	val createDateTime: String? = null,

	@Json(name = "end_day_week")
	val endDayWeek: String? = null,

	@Json(name = "organizer")
	val organizer: String? = null,

	@Json(name = "event_link")
	val eventLink: String? = null,

	@Json(name = "id")
	val id: Int? = null,

	@Json(name = "start_date_time")
	val startDateTime: String? = null,
)
