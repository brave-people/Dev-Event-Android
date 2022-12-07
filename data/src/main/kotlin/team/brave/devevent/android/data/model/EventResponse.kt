/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventResponse.kt] created by Ji Sungbin on 22. 12. 8. 오전 1:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class EventResponse(
    @field:JsonProperty("EventResponse")
    val eventResponse: List<EventResponseItem?>? = null,
)
