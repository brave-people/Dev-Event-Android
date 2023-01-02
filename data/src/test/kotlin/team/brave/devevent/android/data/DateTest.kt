/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DateTest.kt] created by Ji Sungbin on 23. 1. 2. 오후 1:18
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("NonAsciiCharacters")

package team.brave.devevent.android.data

import java.util.Calendar
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.withNotNull
import team.brave.devevent.android.data.mapper.toDate
import team.brave.devevent.android.data.util.buildDate

class DateTest {
    @Test
    fun `ISO 8601 date 변환`() {
        val isoString = "2022-11-28T06:37".toDate()
        val isoDate = buildDate(
            year = 2022,
            month = 10,
            day = 28,
            hour = 6,
            minute = 37,
            second = 0,
            millisecond = 0,
        )

        expectThat(isoString).withNotNull {
            val expect = Calendar.getInstance().apply { time = subject }
            val actual = Calendar.getInstance().apply { time = isoDate }

            get { expect.get(Calendar.YEAR) } isEqualTo actual.get(Calendar.YEAR)
            get { expect.get(Calendar.MONTH) } isEqualTo actual.get(Calendar.MONTH)
            get { expect.get(Calendar.DAY_OF_MONTH) } isEqualTo actual.get(Calendar.DAY_OF_MONTH)
            get { expect.get(Calendar.HOUR_OF_DAY) } isEqualTo actual.get(Calendar.HOUR_OF_DAY)
            get { expect.get(Calendar.MINUTE) } isEqualTo actual.get(Calendar.MINUTE)
        }
    }
}
