/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Event.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:35.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var favorite: Boolean = false,
    val site: String? = "",
    val name: String = "",
    val category: String? = "",
    val headerDate: String = "", // 헤더 날짜
    val joinDate: String? = "", // 신청 날짜
    val startDate: String? = "", // 시작 날짜
    val owner: String? = "" // 주최
) {
    fun contains(value: String) =
        name.contains(value) || category?.contains(value) == true
                || owner?.contains(value) == true || site?.contains(value) == true
}
