/*
 * Designed and developed by Duckie Team, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/duckie-android/blob/develop/LICENSE
 */

package team.brave.devevent.android.data.util

import android.annotation.SuppressLint
import java.util.Calendar
import java.util.Date

@SuppressLint("NewApi")
fun buildDate(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int = 0,
    millisecond: Int = 0,
): Date {
    return Calendar.Builder()
        .setDate(year, month, day)
        .setTimeOfDay(hour, minute, second, millisecond)
        .build()
        .time
}
