/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventViewHolder.kt] created by Ji Sungbin on 23. 1. 18. 오후 11:46
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.presentation.databinding.LayoutEventBinding

class EventViewHolder(private val binding: LayoutEventBinding) : ViewHolder(binding.root) {
    fun setEvent(event: Event) {
        binding.event = event
    }
}
