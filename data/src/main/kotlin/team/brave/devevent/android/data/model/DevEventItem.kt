/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DevEventItem.kt] created by Ji Sungbin on 22. 12. 8. 오전 1:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.fasterxml.jackson.annotation.JsonProperty

// eventTimeType 는 2가지 종류로 나뉨
// 1. DATE: 이벤트 진행 "일시" 를 나타냄
// 2. RECRUIT: 이벤트 "모집" 을 나타냄

data class DevEventItem(
    @field:JsonProperty("event_time_type")
    val eventTimeType: String? = null,

    @field:JsonProperty("start_day_week")
    val startDayWeek: String? = null,

    @field:JsonProperty("description")
    val description: String? = null,

    @field:JsonProperty("cover_image_link")
    val coverImageLink: String? = null,

    @field:JsonProperty("title")
    val title: String? = null,

    @field:JsonProperty("display_sequence")
    val displaySequence: Int? = null,

    @field:JsonProperty("tags")
    val tags: List<TagsItem?>? = null,

    @field:JsonProperty("use_end_date_time_yn")
    val useEndDateTimeYn: String? = null,

    @field:JsonProperty("end_date_time")
    val endDateTime: String? = null,

    @field:JsonProperty("create_date_time")
    val createDateTime: String? = null,

    @field:JsonProperty("end_day_week")
    val endDayWeek: String? = null,

    @field:JsonProperty("organizer")
    val organizer: String? = null,

    @field:JsonProperty("event_link")
    val eventLink: String? = null,

    @field:JsonProperty("id")
    val id: Int? = null,

    @field:JsonProperty("start_date_time")
    val startDateTime: String? = null,

    @field:JsonProperty("use_start_date_time_yn")
    val useStartDateTimeYn: String? = null,
)
