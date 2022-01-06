/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventResponse.kt] created by Ji Sungbin on 22. 1. 7. 오전 12:23
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class EventResponse(
    @PrimaryKey val id: Int = Random.nextInt(),
    val site: String? = "",
    val name: String = "",
    val category: String? = "",
    val headerDate: String = "", // 헤더 날짜
    val joinDate: String? = "", // 신청 날짜
    val startDate: String? = "", // 시작 날짜
    val owner: String? = "" // 주최
)
