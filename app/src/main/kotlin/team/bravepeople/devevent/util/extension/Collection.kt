/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Collection.kt] created by Ji Sungbin on 21. 6. 29. 오전 5:31.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

import team.bravepeople.devevent.activity.main.event.database.EventEntity

fun <E> Collection<E>.takeIfSizeToCategory(n: Int) =
    if (size >= n) "${take(n).joinToString(",")}…" else this.joinToString(",")

fun Collection<EventEntity>.filterNewEventByName(events: List<EventEntity>) =
    this.filterNot { new -> events.any { old -> old.name == new.name } }

fun Collection<EventEntity>.filterChangedEventByFavorite(events: List<EventEntity>) =
    this.filterNot { new -> events.first { old -> old.name == new.name }.favorite == new.favorite }