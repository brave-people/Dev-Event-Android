/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Event.kt] created by Ji Sungbin on 22. 1. 7. 오전 12:19
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.domain.model

import kotlin.random.Random

data class Event(
    val id: Int = Random.nextInt(),
    val site: String? = "",
    val name: String = "",
    val category: String? = "",
    val headerDate: String = "", // 헤더 날짜
    val joinDate: String? = "", // 신청 날짜
    val startDate: String? = "", // 시작 날짜
    val owner: String? = "" // 주최
) {
    fun contains(_value: String): Boolean {
        val value = _value.lowercase()
        return name.lowercase().contains(value) ||
            category?.lowercase()?.contains(value) == true ||
            owner?.lowercase()?.contains(value) == true
    }
}
