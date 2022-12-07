/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [TagsItem.kt] created by Ji Sungbin on 22. 12. 8. 오전 1:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.domain.model

/**
 * [이벤트][Event] 의 태그 모델
 *
 * @param name 태그 이름
 * @param color 표시할 색깔 (hex code)
 */
data class Tag(
    val name: String,
    val color: String,
)
