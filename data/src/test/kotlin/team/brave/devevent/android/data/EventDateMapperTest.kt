/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [MapperTest.kt] created by Ji Sungbin on 23. 1. 2. 오후 1:17
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("NonAsciiCharacters")

package team.brave.devevent.android.data

import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import team.brave.devevent.android.data.mapper.toEventDateString

class EventDateMapperTest {
    @Test
    fun `이벤트 카드 형식에 맞는 날짜 변환 - 다른 일정`() {
        val startDate = "2023-01-02T06:00"
        val endDate = "2023-01-03T08:00"
        val dateString = (startDate to endDate).toEventDateString()

        val expected = "2023.01.02(월) ~ 2023.01.03(화)"

        expectThat(dateString).isEqualTo(expected)
    }

    @Test
    fun `이벤트 카드 형식에 맞는 날짜 변환 - 같은 일정`() {
        val startDate = "2023-01-02T06:00"
        val endDate = "2023-01-02T08:00"
        val dateString = (startDate to endDate).toEventDateString()

        val expected = "2023.01.02(월) 06:00 ~ 08:00"

        expectThat(dateString).isEqualTo(expected)
    }
}
