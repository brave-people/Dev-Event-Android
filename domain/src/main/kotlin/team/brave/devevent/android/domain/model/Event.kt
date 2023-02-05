/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DevEventItem.kt] created by Ji Sungbin on 22. 12. 8. 오전 1:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.domain.model

import team.brave.devevent.android.domain.constants.EventTimeType

/**
 * 이벤트 정보 객체
 *
 * @param id 이벤트 모델의 고유 아이디
 * @param title 이벤트 제목
 * @param organizer 이벤트 주최자
 * @param time 이벤트 진행 일정. 2가지 형식을 나타냄.
 * 1. yyyy.MM.dd(E) ~ yyyy.MM.dd(E)
 * 2. yyyy.MM.dd(E) HH:mm ~ HH:mm
 * @param timeType 표시되는 [이벤트 진행 일정][time] 의 종류.
 * See: [EventTimeType]
 * @param tags 포함된 태그 목록
 * @param link 이벤트 상세 보기 링크
 * @param bannerUrl 이벤트 배너 이미지 링크
 */
data class Event(
    val id: Int,
    val title: String,
    val organizer: String,
    val time: String,
    val timeType: EventTimeType,
    val tags: List<Tag>,
    val link: String,
    val bannerUrl: String?,
)

/**
 * [이벤트 진행 일정][Event.time]을 [일정 타입][Event.timeType]에 맞게
 * 문자열로 나타냅니다.
 */
fun Event.toTimeString(): String {
    return "${timeType.string}: $time"
}
