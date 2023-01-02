/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventResponse.kt] created by Ji Sungbin on 22. 12. 8. 오전 1:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

// [NOTE] 매 달마다 행사가 response 아이템으로 들어옴
// ex) 1월달 행사, 2월달 행사, 3월달 행사가 개별적으로 EventResponseItem 으로 들어옴
//     (month 단위)

internal data class EventResponse(
    val responses: List<EventResponseItem?>? = null,
)
