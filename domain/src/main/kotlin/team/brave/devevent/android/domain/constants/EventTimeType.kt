/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventTimeType.kt] created by Ji Sungbin on 22. 12. 8. 오전 2:49
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.domain.constants

/**
 * 이벤트 진행 일정 타입
 *
 * @property DATE 진행 일시
 * @property RECRUIT 모집 기간
 */
enum class EventTimeType(val string: String) {
    DATE("일시"),
    RECRUIT("모집"),
    ;
}
