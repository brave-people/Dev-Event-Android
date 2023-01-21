/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventItemClickListener.kt] created by Ji Sungbin on 23. 1. 22. 오전 4:41
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.adapter.event

import team.brave.devevent.android.domain.model.Event

interface EventItemClickListener {
    fun onFavoriteClick(event: Event)
    fun onShareClick(event: Event)
}
